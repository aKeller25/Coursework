#include <stdio.h>
#include <string.h>
#include "hashtable.h"

/* Name: Alexander Keller
 * Class: CMSC216
 * UID: 113600425
 * DID: akeller5
 * Time last edited: 6:06 PM 6/22/2016
 */

/*I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment.
 */

#define HASH_NUM 65599

/* Initializes Table table to be empty. Empty here is defined as having a 0
key count and every bucket in the buckets array having a state of EMPTY*/
void init_table(Table *table) {
  int bucket_counter;

  if(table != NULL) {
    table->key_ct = 0;

    for(bucket_counter = 0; bucket_counter < NUM_BUCKETS; bucket_counter++) {
      table->buckets[bucket_counter].state = EMPTY;
    }
  }
}

/* Resets Table table to be empty. For the intent of this project
reset_table and init_table do the samething*/
void reset_table(Table *table) {
  init_table(table);
}

/* Inserts a key and value at the bucket index (the modulus of the hash code
of the key) if the table, key, and value are not null, they key and value
are not longer than the max string size, and the table is not full. It
starts searching for a space to insert it at the bucket index and goes
until it finds a bucket that's empty, deleted, or if the new key is the
same as the current key in the bucket*/
int insert(Table *table, const char *key, const char *value) {
  unsigned long hash_idx, counter;

  if (table != NULL && key != NULL && value != NULL &&
    table->key_ct != (NUM_BUCKETS - 1) && strlen(key) < MAX_STR_SIZE &&
    strlen(value) < MAX_STR_SIZE) {

      hash_idx = hash_code(key) % NUM_BUCKETS;

      for(counter = hash_idx ; counter < NUM_BUCKETS + hash_idx; counter++) {
        if(table->buckets[(counter % NUM_BUCKETS)].state == EMPTY ||
        table->buckets[(counter % NUM_BUCKETS)].state == DELETED) {

          table->buckets[(counter % NUM_BUCKETS)].state = FULL;
          strcpy(table->buckets[(counter % NUM_BUCKETS)].data.key, key);
          strcpy(table->buckets[(counter % NUM_BUCKETS)].data.value, value);

          table->key_ct++;

          return SUCCESS;
        }

        else if (!search(table, key, NULL) &&
        !strcmp(table->buckets[(counter % NUM_BUCKETS)].data.key, key)){

          strcpy(table->buckets[(counter % NUM_BUCKETS)].data.value, value);
          return SUCCESS;
        }
      }
    }
    return FAILURE;
}

/* Searches the table (if the table and key are not null) and returns
FAILURE if it doesn't find it and returns SUCCESS if it finds the key. If
it does find the key and value is not null, value is set to the value
associated with the key that is being searched for*/
int search(Table *table, const char *key, char *value) {
  unsigned long hash_idx, counter;

  if(table != NULL && key != NULL) {
    hash_idx = hash_code(key) % NUM_BUCKETS;

    for(counter = hash_idx; counter < NUM_BUCKETS + hash_idx; counter++) {

      /* If you encounter an empty bucket, then nothing after that will have
      what you're looking for*/
      if(table->buckets[(counter % NUM_BUCKETS)].state == EMPTY) {
        return FAILURE;
      }

      if(table->buckets[(counter % NUM_BUCKETS)].state != DELETED &&
        !strcmp(table->buckets[(counter % NUM_BUCKETS)].data.key, key)) {

          if(value != NULL) {
            strcpy(value, table->buckets[(counter % NUM_BUCKETS)].data.value);
          }
          return SUCCESS;
        }
      }
    }
  return FAILURE;
}

/* Deletes a bucket (if table and key are not null and the key is in the
table) by setting the state of the bucket to empty and decrementing the
key count*/
int delete(Table *table, const char *key) {
  unsigned long hash_idx, counter;

  if(table != NULL && key != NULL && !search(table, key, NULL)) {
    hash_idx = hash_code(key) % NUM_BUCKETS;

    for(counter = hash_idx; counter < NUM_BUCKETS + hash_idx; counter++) {

      if(table->buckets[(counter % NUM_BUCKETS)].state != DELETED &&
        !strcmp(table->buckets[(counter % NUM_BUCKETS)].data.key, key)) {

          table->buckets[(counter % NUM_BUCKETS)].state = DELETED;
          table->key_ct--;

          return SUCCESS;
        }
      }
    }
      return FAILURE;
}

/* Returns the key count if Table table is not null, otherwise
returns FAILURE*/
int key_count(Table *table) {
  int num_of_keys = FAILURE;

  if(table != NULL) {
    num_of_keys = table->key_ct;
  }

  return num_of_keys;
}

/* Returns the bucket count if Table table is not null, otherwise
returns FAILURE*/
int bucket_count(Table *table) {
  int num_of_buckets = FAILURE;

  if(table != NULL) {
    return num_of_buckets = NUM_BUCKETS;
  }

  return num_of_buckets;
}

/* Returns the hash code of the key (if the key is not null) by using
the formula (hash_code(key - the last character of the key) * HASH_NUM) +
the last character of the key*/
unsigned long hash_code(const char *key) {
  if(key != NULL) {
    char new_key[MAX_STR_SIZE] = "";

    if(strcmp(key, "")) {
      strcpy(new_key, key);

      new_key[strlen(key) - 1] = '\0';

      return ((hash_code(new_key)) * HASH_NUM) + key[strlen(key) - 1];
    } else {
      return SUCCESS;
    }
  }
  return SUCCESS;
}
