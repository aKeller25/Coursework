#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab08.py
# Date created: 8/10/2017
# Last edited: 11:55 PM 8/10/2017
# Variables used: station, lat, lon, year, months, download_url,
#   infile, indata, outfile, combined, name, month_file, tmp_line
# This program is used to read data from a URL, format 3 months of data
#   into their own files, and then combine them with my name
###################################

# Importing arcpy so this program can access its functions
import urllib

# Setting up the the station, station information, and collection data
station = "KMDCOLLE10"

lat = 38.972
lon = -76.924
year = 2016
months = [4, 6, 9]

# Downloading the data from the url and saving it in a file
for m in months:
    download_url = "https://www.wunderground.com/weatherstation/WXDailyHistory.asp?ID=KMDCOLLE10&year=2016&month={}&graphspan=month&format=1".format(m)

    infile = urllib.urlopen(download_url)
    indata = infile.read()
    outfile = "{}_{}{}.txt".format(station, year, str(m).zfill(2))

    with open(outfile, "w") as of:
        of.write(indata)


# Setting variable for merging the files
combined = "weather_data_all_lab08.txt"
name = "Alex"

# Cleanging and merging the 3 files (April, September, and June) together
with open(combined, 'w') as open_file:
    # Header for data along with my name
    open_file.write("Date, THighF, TAvgF, TLowF, DPHighF, DPAvgF, DPLowF, HHigh, HAvg, HLow, PrMinIn, WSAvgMPH, GCMaxMPH, PreSumIn, name\n")

    for m in months:
        month_file = "{}_{}{}.txt".format(station, year, str(m).zfill(2))

        with open(month_file) as mf:
            days = 30

            # Skipping the first 2 lines in order to get to the actual data
            mf.readline()
            mf.readline()

            # Starting at 1 because months start with the first day and not the 0th
            for d in xrange(1, days + 1):

                # Reading and formating the line
                tmp_line = mf.readline()
                tmp_line = tmp_line.strip("\n")

                # Writing the line to the file
                open_file.write("{}, {}, {}, {}\n".format(tmp_line, lat, lon, name))
                mf.readline()

print "Done"
