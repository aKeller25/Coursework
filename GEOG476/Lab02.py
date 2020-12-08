#!/usr/bin/env python
# coding: utf-8

# Class: GEOG476
# Semester: FALL2018
# Assignment: Lab01
# Author: Alex Keller
# Written: 9:56 AM 9/12/2018
# Last edited: 9:56 AM 9/12/2018

# Psuedo code:
#             Encryption:
#             1. Take in message
#             2. With a for loop, go through the word, character by character
#             3. Convert to ASCII number, while appending it to a new list
#             4. Add 1,2,3,etc to the number until it(the number added) has reached the specified encryption length, 
#             start back at 1 if it has reached that length
#             5. Convert back to ASCII character
#             6. Do steps 3-6 until the message has been fully encrypted
#             7. Concatonate the list into a single string
#             8. Return the encrypted string
#             
#             Decryption:
#             1. Do all the same steps as encryption, but instead of adding, you subtract

# read ch.1 1-11 & ch.2 27-35 
# http://books.tarsoit.com/Python%203%20Object-oriented%20Programming%20-%20Second%20Edition.pdf
# 
# encyptionLegnth = 3
# 
# +1,+2,+3,+1,+2,+3

# In[7]:


### Printing message
message = "Hello, My name is Andres Garcia. I am a GIS major. This is my last year of college, I am expected to graduate next spring.I am interested in Data Collection. My hobbies include whitewater kayaking and mountain biking with my dog.My goals for this class are to become more proficient in python and learn more aspects of object-oriented programming.Alexander, I think you can help me achieve my goal by sharing your knowledge."
print('Original:  ' + message + '\n')


### Encrypting and printing the message
# NOTE: char + 1; char + 2; char + 3 (encr_leng = 3)
def encrypt(message, encr_len):
    encrypted = []

    for character in range(len(message)): # Iterates through each character in a word

        encrypted.append(ord(message[character]))   # Converts the letter to ascii
        encrypted[character] += (1 + (character  % encr_len))             # Adds on the current index to the character (padded by 1 since it's 0-indexed)
        encrypted[character] = chr(encrypted[character])    # Converts the ascii character to ascii
                                 
    encr_message = "".join(encrypted)    # Concatonates the characters into a string
                
    return encr_message  

encr_message = encrypt(message, 10)          

print('Encrypted: ' + encr_message + '\n')  # Prints out the encrypted text



# In[12]:


encr_message = '''Igopt2'U‚*ocpi%oz(Jxethw%Mhzlsb0#M%gt(j*HKV$rgqw{8!Vkmx&p{)wz"oexz' nks"rj%ivtuohg/$N&hu)oyrhgykk(}y!iuei{h|n*og{x%ywzrxh0#M%gt(rxuguixzll)so"Geyg'Kxvmgfxnuu6)Wz"ksghpm|*jpfpzjl(€rjvh{fzlz)ub{dontn(jxe"pszt{irx!dlontn(€suj#q~&kwp8!O|$luht|*gqu$ynp{)mmcvw%gym)~p"eihutm)wpth$uxvnrmjgqx%ou(yƒujrr%gul)vfcur%svzn*busihzz(xp!qenji{5x|jgqxjj'x{yhtdqrouo7*Bnh|ftkm{6!K#xmous)ƒpw#gft'pnvq"pi%gjprowg#q~&nwjv!d|$xnhzrxh"|szx'swyxnhhlk5'''
### Decrypting and printing the message
# NOTE: char + 1; char + 2; char + 3 ; char + 1 (encr_leng = 3)
def decrypt(encr_message, encr_len):
    decrypted = []

    for character in range(len(encr_message)): # Iterates through each character in a word

        decrypted.append(ord(encr_message[character]))          # Converts the letter to ascii
        decrypted[character] -= (1 + (character  % encr_len))   # Adds on the current index to the character (padded by 1 since it's 0-indexed)
        decrypted[character] = chr(decrypted[character])        # Converts the ascii character to ascii
                                 
    decr_message = "".join(decrypted)    # Concatonates the characters into a string
                
    return decr_message  

decr_message = decrypt(encr_message, 10)           

print('Decrypted: ' + decr_message + '\n')  # Prints out the encrypted text


# For Andres
# 
# My name is Alex Keller and I am a super senior GIS major. I am planning to graduate next fall, but I am not sure if I want to take on a minor. My goal for this class is to integrate object oriented programming into GIS programs. I think you, Andres, can help by helping me better understand the material by talking through it with each other.
# 
# N{!pbof"ju!Cmgy"Lgmnft!cof!K!cn"b"twqgs"tgokpt!IJU!oblpt/"J"bo!rmcopjph"uq!iscewbvf"ogyv!hbnm.!dvv!K!cn"oqu"twsg!kg"J"xcov!vp"uclg!qo"b"nkoqs0!Oz"hqbn!hpt!vikt"dnbut"ju!vp"jpughtbvf"pdkgdv!qskfpuge"qtpiscnojph"jpuq!IJU!rsqhtbot0!K!vikom!{pw-"Bpetfu-"dco"igmr!dz"igmrjph"ng!dfvugs"vpegsuucof!vig!obvftjcm"c{!vbnlkoi!vitpwhj!ku"xkuj!gbei"pvigs0
# 
# len = 2 
# 
# For Samir:
# 
# Dear Samir, My name is Alex Keller and I am a super senior GIS major. Even though I am close to graduation, I am thinking of taking on a minor and/or study abroad, but I am still wondering what I want to do. My goal for this class is to integrate object oriented programming into GIS programs. I think you can help me achieve this goal by understanding someone else's perspective on the material learned in class. Sincerely, Alex Keller
# 
# Egdv%Ybolv1&N{#rfsf"lw%Gmg{$Pkmnhv%gof#M%gn"d$x{qgu$xkokrv%MJU#qfppt1$J|fp#xmuvik$N&bo#gqutg#xt&htdhzgukrr1&J"dq%zikqonth"rj%zbmlrl&pp#e%sjprv%gof2sw&tvxh~&bdusfj-"eyy&J"dq%yukop%}ppgiwooi#{mgu"L$|gov#xt&eq1$R!ireq&gqu$ynju#gqgtu#mx&uq#mszfiueyk!qenjiu"rvnkovhh%vsqjvfsnkqk%oovr$LOT"svtmscpw3&J"wlntl"|sz&dcq$mkmr#qj&bekmj|f"wlny!ireq&c{#ysjftvxftekqk%ypohssk!gowj-t"siwyqgfxn|f"rr%zig#qfzftleq&mgdvske"lr%imcvw3&Tkqgjxfn|0%Gmg{$Pkmnhv
# 
# 
# len = 6
# 
# 
# For Mark
# 
# My name is Alex Keller and I am a super senior GIS major. I am close to graduation, but I still feel like I want to do more here. My goal for this class is to integrate object oriented programming into GIS programs. I think you can help me achieve this goal by providing a different perspective on the class material.
# 
# N{#rboh$ju#Emg{$Lgopft#eof#M!cp$b"vyqgu$tgqmpt#KJU#qblrv/"L$bo#gmqvi!vr$htdhvcwmpp/$cww$J"vxjno$gghp!nlof"L$xcqx!vr$eq#qpth$igui/"P}!irem"iss"wlju#gmcvw!kv$uq#movhkscwi!qenfew$ptliovhh!rushtdqnkqk!kqxp"JMT"svpiuenu1$J"wljpn$zqx$dcq$igot!oh$bekmfxh$ujlw!irem"e}!ruswkgmoi#e!fljgguiov#tftvtfewmwg#so"wlf"fpbuv$ncwiskdp/
# 
# len = 4

# From Andres:
# 
# len = 10
# 
# Igopt2'U‚*ocpi%oz(Jxethw%Mhzlsb0#M%gt(j*HKV$rgqw{8!Vkmx&p{)wz"oexz' nks"rj%ivtuohg/$N&hu)oyrhgykk(}y!iuei{h|n*og{x%ywzrxh0#M%gt(rxuguixzll)so"Geyg'Kxvmgfxnuu6)Wz"ksghpm|*jpfpzjl(€rjvh{fzlz)ub{dontn(jxe"pszt{irx!dlontn(€suj#q~&kwp8!O|$luht|*gqu$ynp{)mmcvw%gym)~p"eihutm)wpth$uxvnrmjgqx%ou(yƒujrr%gul)vfcur%svzn*busihzz(xp!qenji{5x|jgqxjj'x{yhtdqrouo7*Bnh|ftkm{6!K#xmous)ƒpw#gft'pnvq"pi%gjprowg#q~&nwjv!d|$xnhzrxh"|szx'swyxnhhlk5
# 
# Hello, M‑ name is Andres Garcia. I am a GIS major. This is my last ear of college, I am expected to graduate next spring. I am interested in Data Collection. My hobbies include ₣hitewater kayaking and mountain biking ₣ith my dog. My goals for this class are to become more proficient in pƈthon and learn more aspects of object-oriented programming. Alexander, I think ƈou can help me achieve my goal by sharing your knowledge.
# 
# 
# 

# From Mark:
# 
# len = 3
# 
# Ik/!o|!pdng#ju#Ncul"Ipyoft#bpg!K*n"d!uhokrs"|fcu!CRTE#ncmpt#bpg!ILT"pjprs0#J"kprh!vr!iubfxbvh!kq!vkf"vqtloi1!O|!jrcdlfu#jpfmwgf"kjmloi#bpg!mdzcnjpj/"L!jrqg#uq#bfybpff"pz"noqzmgghg#ph#q{wiqq!erekqh"lo"wikv!eobuv-"zikfi"vfgpt"wp"ef"wig#vr#bpg!ernkqh"fpfloi#mcqhwdhg#jp#n{#gkhmf1!K#iqsf"|pw#dcq!jhmr#c{#xqulkqh"zjvk!oh!qq!vkfuh!ndcu#bpg!jhmrloi#qtrwkgf"gjhifthov#qgutrhdvlwgv!qq!vkf"fppwfpw!yh!fltextu1
# 
# Hi, my name is Mark Fowler and I'm a senior year AOSC major and GIS minor. I hope to graduate in the spring. My hobbies include hiking and kayaking. I hope to advance my knowledge of python coding in this class, which seems to be the up and coming coding language in my field. I hope you can help by working with me on these labs and helping provide different perspectives on the content we discuss.

# From Samir:
#     
# len = 4
# 
# Igopp.#qz"qeng#mt"Venku$Ljdhlc1$J"dq!c#KJU#qblrv!cqh!vkmt"lw!o|$mcvx!uhqfuwis"rj!erpmgji/"L$xkop!dh$htdhvcwmoi#xikv$Egfindhv/"L$mkni!vr$hq#ljmlrh"dre"fenrlrh0#$J"kmu"wlf"wvbkow!kq$Tjhrbpgsbj#Rbvlsoco$Qcuo!sxmug#sgvhr/"P}!irem"iss"wlju#gmcvw!kv$uq#pfcur!r|xiqq$nqui!vkssqxkin|$bpg$vpgisuweof#muu#eqromdcwmpp#jpt#KJU#vfndxff#tsqmidvv2!Coiy"L$ujlrl"|sv"feo"kimr#qf"dgikhzf"p}!irem"e}!yrvlkqk!vrkfvkis"gyskqk!ndf!uhwtkrrt0
# 
# Hello, my name is Samir Khadka. I am a GIS major and this is my last semester of college. I will be graduating this December. I like to go hiking and camping.  I hit the trails in Shenandoah National Park quite often. My goal for this class is to learn python more thoroughly and understand its application for GIS related projects. Alex I think you can help me achieve my goal by working together during lab sessions.
