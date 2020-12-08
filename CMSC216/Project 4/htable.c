#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "htable.h"
#include "my_memory_checker_216.h"

/* Name: Alexander Keller
* Class: CMSC216
* UID: 113600425
* DID: akeller5
* Time last edited: 11:36pm 7/8/2016
*/

/*I pledge on my honor that I have not given or received any unauthorized
* assistance on this assignment.
*/


unsigned int hash_code(const char *str);
static Bucket *find_bucket(const Table * table, const char *key, Bucket ** prev);

/* If the Tabe table is not NULL, its size not 0, and all of the memory
allocations associated with creating a table object passs, then a table will
be initialized with the values provided and retun SUCCESS. Otherwise the
function will return FAILURE.*/
int create_table(Table ** table, int table_size, void (*free_value) (void *)) {
  int bucket_idx;

  if (table && table_size != 0) {

    *table = malloc(sizeof(Table));

    if (*table != NULL) {

      (*table)->key_count = 0;
      (*table)->table_size = table_size;
      (*table)->free_value = free_value;

      (*table)->buckets = malloc(sizeof(Bucket *) * table_size);

      if ((*table)->buckets != NULL) {

        for (bucket_idx = 0; bucket_idx < table_size; bucket_idx++) {
          (*table)->buckets[bucket_idx] = NULL;
        }
        return SUCCESS;
      }
    }
  }
  return FAILURE;
}

/* If the table is not NULL, the table will be cleared, all
dynamically allocated memory associated with it will be freed and the
function will return SUCCESS. Otherwise it will return FAILURE.*/
int destroy_table(Table *table) {

  if (table) {

    clear_table(table);
    free(table->buckets);
    free(table);

    return SUCCESS;
  }
  return FAILURE;
}

/* If the table and key are not NULL, the function will continue,
if they are NULL, the function will return FAILURE.*/
int put(Table *table, const char *key, void *value) {

  if (table && key) {
    /* The function will check to see if the key associated with the bucket
     is already present in the table.*/

    Bucket *old, *found = find_bucket(table, key, NULL);
    int bucket_index = (hash_code(key) % table->table_size);

    /* If the key is not found, it will check if there isn't already a
    linked-list chain assocaited with the bucket.*/
    if (found == NULL) {

      /* If there is not a chain assocaited, then a chain will be made and
      the assocated memory dynamically allocated*/
      if (table->buckets[bucket_index] == NULL) {

        /* If the allocations are succesful, then the new key will be
        inserted with it's associated values, the table's key count
        will be increased, and the function will return SUCCESS. If not then*/

        table->buckets[bucket_index] = malloc(sizeof(Bucket));

        if (table->buckets[bucket_index] != NULL) {

          table->buckets[bucket_index]->key = malloc(strlen(key) + 1);

          if (table->buckets[bucket_index]->key != NULL) {

            strcpy(table->buckets[bucket_index]->key, key);

            table->buckets[bucket_index]->value = value;
            table->buckets[bucket_index]->next = NULL;

            table->key_count++;

            return SUCCESS;
          }
        }
      } else {
        /* If there is a chain associated, the first bucket in the chain will
        be stored in the Bucket old and the new bucket the same way as
        previously stated*/

        old = table->buckets[bucket_index];

        table->buckets[bucket_index] = malloc(sizeof(Bucket));

        if (table->buckets[bucket_index] != NULL) {

          table->buckets[bucket_index]->key = malloc(strlen(key) + 1);

          if (table->buckets[bucket_index]->key != NULL) {

            strcpy(table->buckets[bucket_index]->key, key);

            table->buckets[bucket_index]->value = value;
            table->buckets[bucket_index]->next = old;

            table->key_count++;

            return SUCCESS;
          }
        }
      }
    } else {
      if (table->free_value && found->value) {
        table->free_value(found->value);
      }
      found->value = value;
    }
  }
  return FAILURE;
}

/* If table and key are not NULL, the function will continue.
Otherwise the function will return FAILURE. If the bucket is found and it's
value is not NULL, then value will be set to it's value paramater. If it's
found and it's value is NULL, value will be set to NULL and the function will
return FAILURE*/
int get_value(const Table * table, const char *key, void **value) {
  if (table && key) {
    if (find_bucket(table, key, NULL)) {
      *value = find_bucket(table, key, NULL)->value;

      return SUCCESS;
    } else {
      *value = NULL;

    }
  }
  return FAILURE;
}

/*If table is not NULL, the function will return the number of keys
in the table. Otherwise it will return FAILURE.*/
int get_key_count(const Table *table) {
  if (table) {
    return table->key_count;
  }
  return FAILURE;
}

/* If the table and key are not NULL and the key is in the table it
continues to remove the entry assocaited with the key, otherwise return
FAILURE.*/
int remove_entry(Table *table, const char *key) {
  int target_idx = hash_code(key) % table->table_size;
  Bucket *prev, *target = find_bucket(table, key, &prev);

  if (table && key && target) {

    /* If the target to remove is the first in the chain in the bucket
    it's located at, set the first in the chain to the one after it.
    Otherwise the previous bucket is set to the one after the target.*/
    if (table->buckets[target_idx] == target) {

      table->buckets[target_idx] = target->next;

    } else {
      prev = target->next;
    }

    /* If the table's free_value is not NULL and it's target value is not
    NULL, it frees the assocaited value with the table's free_value function
    and free's the rest of the data assocaited with the target and decreases
    the table's key count.*/
    if (table->free_value && target->value) {

      table->free_value(target->value);
      free(target->key);
      free(target);

      table->key_count--;

      return SUCCESS;
    } else {

      /*Free's the data assocaited with the target and decreases the
      table's key count*/
      free(target->key);
      free(target);

      table->key_count--;
      return SUCCESS;
    }
  }
  return FAILURE;
}

/* This function clears the table if the table is not NULL. Otherwise it just
returns FAILURE*/
int clear_table(Table *table) {
  Bucket *current, *next;
  int counter;

  /* Goes through the table and calls remove_entry on each element in each
  chain in each bucket*/
  if (table) {
    for (counter = 0; counter < table->table_size; counter++) {
      current = table->buckets[counter];

      while (current != NULL) {
        next = current->next;

        remove_entry(table, current->key);

        current = next;
      }
    }
  }
  return FAILURE;
}

/* If the table is not NULL and the table has 0 keys in it, the function will
return SUCCESS. Otherwise it will return 0*/
int is_empty(const Table *table) {
  if (table && !(table->key_count)) {
    return SUCCESS;
  }
  return 0;
}

/* If the table is not NULL, the function will return the table's size.
Otherwise it will return FAILURE*/
int get_table_size(const Table *table) {
  if (table) {
    return table->table_size;
  }
  return FAILURE;
}

/* If the table and key are not NULL this function searches for the bucket.*/
static Bucket *find_bucket(const Table *table, const char *key,
  Bucket **prev) {

  if (table && key) {
    int bucket_num = hash_code(key) % table->table_size;

    Bucket *current = table->buckets[bucket_num];

    /* Searches for the link in the bucket chain until the list is exhausted*/
    while (current != NULL) {
      if (!strcmp(current->key, key)) {
        return current;
      }
      if (prev != NULL) {
        *prev = current;
      }
      current = current->next;
    }
  }
  return NULL;
}

/*
* Do not modify this hash_code function.
* Leave this function at the end of the file.
* Do not add a prototype to the htable.h file
* for this function.
*
*/

unsigned int hash_code(const char *str) {
  unsigned int code;

  for (code = 0; *str; str++) {
    code ^= ((code << 3) | (code >> 29)) + (*str);
  }

  return code;
}
