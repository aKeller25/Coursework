#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "bag.h"

/* Name: Alexander Keller
* Class: CMSC216
* UID: 113600425
* DID: akeller5
* Time last edited: 7:59pm 7/15/2016
*/

/*I pledge on my honor that I have not given or received any unauthorized
* assistance on this assignment.
*/

static Sub_Bag *find_bag(Bag *bag, const char *element);

/* Initializes the bag with a size of 0*/
void init_bag(Bag *bag) {
  if (bag != NULL) {
    bag->bag_size = 0;
    bag->Sub_Bags = NULL;
  }
}

/* Adds an element to the bag if the bag and element are not NULL*/
void add_to_bag(Bag *bag, const char *element) {
  Sub_Bag *found, *current, *prev, *new;

  if (bag != NULL && element != NULL) {
    found = find_bag(bag, element);

    /* If the bag (element here) is not found in the array, make one and add
    it*/
    if(found == NULL) {
      new = malloc(sizeof(Sub_Bag));

      if(new != NULL) {
        new->name = malloc(strlen(element) + 1);

        if(new->name != NULL) {
          /*Makes the new element*/
          strcpy(new->name, element);
          new->prev = NULL;
          new->num_occurences = 1;

          if(bag->Sub_Bags == NULL) { /*If the lsit is empty*/
            bag->Sub_Bags = new;
            new->next = NULL;
            new->prev = NULL;

            bag->bag_size++;
          } else {
            current = bag->Sub_Bags;

            /* Iterates to the end of the array*/
            while(current != NULL) {
              prev = current;
              current = current->next;
            }
            new->prev = prev;
            new->next = prev->next;

            prev->next = new;

            bag->bag_size++;
          }
        } else {
          printf("Memory allocation has failed\n");
          exit(-1);
        }
      } else{
        printf("Memory allocation has failed\n");
        exit(-1);
      }
    } else { /* If the element is in the bag, just increase the number of
      occurences of the element*/
      (found->num_occurences)++;
    }
  }
}

/* Returns the size (number of elements in the bag)*/
size_t size(Bag bag) {
  return bag.bag_size;
}

/* If the element is in the array, this function returns the number of
occurences of the element. Otherwise it returns -1*/
int count(Bag bag, const char *element) {
  Sub_Bag *found_bag;

  if(element != NULL) {
    found_bag = find_bag(&bag, element);

    if(found_bag != NULL) {
      return found_bag->num_occurences;
    }
  }
  return -1;
}

/* If the bag and the element are not NULL, the bag exists, and the number
of occurences of the bag are greater than 1, it removes one of the occurences
of the element and returns the remaining number of elements. Otherwise it
returns -1*/
int remove_occurrence(Bag *bag, const char *element) {
  Sub_Bag *found_bag;

  if(bag != NULL && element != NULL) {
    found_bag = find_bag(bag, element);

    if(found_bag != NULL && found_bag->num_occurences > 1) {
      return (--(found_bag->num_occurences));
    }
  }
  return -1;
}

/* Removes all occurrences of the element and returns 0 if it succeeds and
returns -1 otherwise*/
int remove_from_bag(Bag *bag, const char *element) {
  Sub_Bag *found_bag;

  if(bag != NULL && element != NULL) {
    found_bag = find_bag(bag, element);

    if(found_bag != NULL) {

      /* Removes all occurrences but the last one*/
      while(found_bag->num_occurences > 1) {
        remove_occurrence(bag, element);
      }
      /* Removes the last occurrence*/
      /* The element to remove is the first and/or last element*/
      if(found_bag->prev == NULL) {

        bag->Sub_Bags = found_bag->next;

        /* If there is only one element in the array*/
        if(found_bag->next != NULL) {
            found_bag->next->prev = NULL;
        }
        free(found_bag->name);
        free(found_bag);

        (bag->bag_size)--;
      } else {
        /* Removes if the element otherwise*/
        found_bag->prev->next = found_bag->next;

        /* If you're trying to remove the last element in the array*/
        if(found_bag->next != NULL) {
            found_bag->next->prev = found_bag->prev;
        }

        free(found_bag->name);
        free(found_bag);

        (bag->bag_size)--;
      }
      return 0;
    }
  }
  return -1;
}

/* Creates and returns a union of bag1 and bag2*/
Bag bag_union(Bag bag1, Bag bag2) {
  Sub_Bag *current = bag1.Sub_Bags, *current2 = bag2.Sub_Bags, *found;
  Bag result_bag;

  init_bag(&result_bag);

  /* Adding elements from bag1*/
  while (current != NULL) {
    add_to_bag(&result_bag, current->name);
    current = current->next;
  }
  /* Adding element from bag2*/
  while(current2 != NULL) {
    found = find_bag(&result_bag, current2->name);

    if(found == NULL) { /*If the elt isn't in result_bag, add it*/
      add_to_bag(&result_bag, current2->name);
    } else { /*If it is, then add their occurences together*/
      found->num_occurences += current2->num_occurences;
    }
    current = current->next;
  }
  return result_bag;
}

/* Checks if bag1 is a sub bag2 and returns 1 if so and returns 0
otherwise*/
int is_sub_bag(Bag bag1, Bag bag2) {
  Sub_Bag *current = bag2.Sub_Bags, *found;

  /* If bag1 has a size of 0, then it is automatically a sub bag of bag2*/
  if(bag1.bag_size == 0) {
    return 1;
  }

  /* There is no way bag1 is a sub bag of bag2 if bag1 is bigger in size
  than bag2*/
  if(bag1.bag_size <= bag2.bag_size) {
      while(current != NULL) {

        /* If an element in bag2 is NOT in bag1 or an element in bag2 IS in
        bag1, but bag1 has more occurences of the element*/
        if ((found = find_bag(&bag1, current->name))
         == NULL || (found->num_occurences > current->num_occurences)) {
           return 0;
         }

         current = current->next;
      }
    return 1;
  }
  return 0;
}

/* Clears the bag (if the bag exists) by calling remove_from_bag on every
element in the bag*/
void clear_bag(Bag *bag) {
  Sub_Bag *current, *next;

  if(bag != NULL) {
    current = bag->Sub_Bags;

    while(current != NULL) {
      next = current->next;

      remove_from_bag(bag, current->name);

      current = next;
    }
  }
}

/* Finds and returns the Sub_Bag with the name as element if the bag and
the are not NULL and the array of Sub_Bag's exist*/
static Sub_Bag *find_bag(Bag *bag, const char *element) {
  Sub_Bag *current = NULL;

  /* If bag and element are not NULL and there are no elements in the array*/
  if (bag != NULL && element != NULL && bag->Sub_Bags != NULL) {
    current = bag->Sub_Bags;

    while (current != NULL) {
      if (!strcmp(current->name, element)) {
        return current;
      }
      current = current->next;
    }
  }
  return current;
}
