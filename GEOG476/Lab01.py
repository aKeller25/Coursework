#!/usr/bin/env python
# coding: utf-8

# Class: GEOG476
# Semester: FALL2018
# Assignment: Lab01
# Author: Alex Keller
# Written: 10:17 AM 9/5/2018
# Last edited: 10:16 AM 9/10/2018
# 
# Notes: http://pythontutor.com & http://www.asciitable.com/ are a good resources
# Deliminate the sec 4 strings by using '/'
# 
# Separate each word into it's own separate string

# Section 1

# In[50]:


### Printing message
message = "I look forward to working with you!"
print('Original:      ' + message + '\n')


### Encrypting and printing message
def encrypt(message):
    encrypted = []
    encrypted_final = []
    single_word = []
    sentences = []
    
    #for i in range(len(message)): # Individual sentences 
        
    # From here down works for a message that only has 1 sentence
    
    words = message.split(" ") # Breaks the sentence into words

    for word in words: # Iterates through each word

        for character in range(len(word)): # Iterates through each character in a word

            single_word.append(ord(word[character]))   # Converts the letter to ascii
            single_word[character] += (character + 1)             # Adds on the current index to the character (padded by 1 since it's 0-indexed)
            single_word[character] = chr(single_word[character])    # Converts the ascii character to ascii

        encrypted.append(single_word)
        ## Make it so it doesn't append a space onto the last word of the list
        encrypted.append(" ")

        single_word = []            

    for x in range(len(encrypted)):         # Flattens the nested lsits into a single list
        for y in encrypted[x]:
            encrypted_final.append(y)
        
    sentence = "".join(encrypted_final)    # Concatonates the characters into a string
    
    return sentence
                                

encr_message = encrypt(message)           

print('Encrypted:     ' + encr_message + '\n')  # Prints out the encrypted text


### Decrypting and printing message
def decrypt(encr_message):
    decrypted = []
    decrypted_final = []
    single_word = []
    
   ## for i in range(len(message)): # Individual sentences 
        
    # From here down works for a message that only has 1 sentence
    
    words = encr_message.split(" ") # Breaks the sentence into words
        
    for word in words: # Iterates through each word
            
        for character in range(len(word)): # Iterates through each character in a word
                
            single_word.append(ord(word[character]))   # Converts the letter to ascii
            single_word[character] -= (character + 1)             # Adds on the current index to the character (padded by 1 since it's 0-indexed)
            single_word[character] = chr(single_word[character])    # Converts the ascii character to ascii
            
        decrypted.append(single_word)
        ## Make it so it doesn't append a space onto the last word of the list
        decrypted.append(" ")
            
        single_word = []            
        
    for x in range(len(decrypted)):
        for y in decrypted[x]:
            decrypted_final.append(y)
            
    sentence = "".join(decrypted_final)    # Concatonates the characters into a string
                
    return sentence 

decr_message = decrypt(encr_message)               # Concatonates the characters into a string

print('Decrypted:     ' + decr_message + '\n' + '\n')


### Names, emails, and phone numbers
james = "James Whyte  jhwumd@gmail.com  2406409005"
daniel = "Daniel Ataalla  fakeEmail@gmail.com  3017420207"

print('James original: ' + james + '\n')

encr_james = encrypt(james)

print('James encoded: ' + encr_james + '\n')
print('James decrypted: ' + decrypt(encr_james) + '\n' + '\n')


print('Daniel original: ' + daniel + '\n')

encr_daniel = encrypt(daniel)

print('Daniel encoded: ' + encr_daniel + '\n')
print('Daniel decrypted: ' + decrypt(encr_daniel) + '\n')


# In[ ]:





# In[51]:


### Printing message
message = "I look forward to working with you!"
print('Original:      ' + message + '\n')


### Encrypting and printing message with own encryption
def encrypt(message, num):
    encrypted = []
    encrypted_final = []
    single_word = []
    sentences = []
    
    ##for i in range(len(message)): # Individual sentences 
        
    # From here down works for a message that only has 1 sentence
    
    words = message.split(" ") # Breaks the sentence into words

    for word in words: # Iterates through each word

        for character in range(len(word)): # Iterates through each character in a word

            single_word.append(ord(word[character]))   # Converts the letter to ascii
            single_word[character] += ((character + 1) * num)             # Adds on the current index to the character (padded by 1 since it's 0-indexed)
            single_word[character] = chr(single_word[character])    # Converts the ascii character to ascii

        encrypted.append(single_word)
        ## Make it so it doesn't append a space onto the last word of the list
        encrypted.append(" ")

        single_word = []            

    for x in range(len(encrypted)):         # Flattens the nested lsits into a single list
        for y in encrypted[x]:
            encrypted_final.append(y)
        
    sentence = "".join(encrypted_final)    # Concatonates the characters into a string
                
    return sentence                        

encr_message = encrypt(message, 3)           

print('Encrypted:     ' + encr_message + '\n')  # Prints out the encrypted text


### Decrypting and printing message of my own design
def decrypt(encr_message, num):
    decrypted = []
    decrypted_final = []
    single_word = []
    
   ## for i in range(len(message)): # Individual sentences 
        
    # From here down works for a message that only has 1 sentence
    
    words = encr_message.split(" ") # Breaks the sentence into words
        
    for word in words: # Iterates through each word
            
        for character in range(len(word)): # Iterates through each character in a word
                
            single_word.append(ord(word[character]))   # Converts the letter to ascii
            single_word[character] -= ((character + 1) * num)           # Adds on the current index to the character (padded by 1 since it's 0-indexed)
            single_word[character] = chr(single_word[character])    # Converts the ascii character to ascii
            
        decrypted.append(single_word)
        ## Make it so it doesn't append a space onto the last word of the list
        decrypted.append(" ")
            
        single_word = []            
        
    for x in range(len(decrypted)):
        for y in decrypted[x]:
            decrypted_final.append(y)
            
    sentence = "".join(decrypted_final)    # Concatonates the characters into a string
                
    return sentence 

encr_message = 'L ouxw iu{py wu zu{wx| zo}t |u~-'

decr_message = decrypt(encr_message, 3)               # Concatonates the characters into a string

print('Decrypted:     ' + decr_message + '\n' + '\n')

### Names, emails, and phone numbers
james = "James Whyte  jhwumd@gmail.com  2406409005"
daniel = "Daniel Ataalla  fakeEmail@gmail.com  3017420207"

print('James original: ' + james + '\n')

encr_james = encrypt(james, 5)

print('James encoded: ' + encr_james + '\n')
print('James decrypted: ' + decrypt(encr_james, 5) + '\n' + '\n')


print('Daniel original: ' + daniel + '\n')

encr_daniel = encrypt(daniel, 5)

print('Daniel encoded: ' + encr_daniel + '\n')
print('Daniel decrypted: ' + decrypt(encr_daniel, 5) + '\n')


# In[ ]:




