#if !defined(BAG_IMPLEMENTATION_H)
#define BAG_IMPLEMENTATION_H

typedef struct sub_bag{
   char *name;
   int num_occurences;
   struct sub_bag *prev;
   struct sub_bag *next;
} Sub_Bag;

typedef struct {
   int bag_size;
   Sub_Bag *Sub_Bags;
} Bag;

#endif
