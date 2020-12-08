#include <stdio.h>
#include <string.h>
#include <sysexits.h>
#include "hashtable.h"

/* Name: Alexander Keller
 * Class: CMSC216
 * UID: 113600425
 * DID: akeller5
 * Time last edited: 12:33 PM 6/28/2016
 */

/*I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment.
 */

#define MAX_LINE_CHAR 1024

int main(int argc, char const *argv[]) {
  FILE *input_stream;
  char line_to_parse[MAX_LINE_CHAR + 1], cmd[MAX_LINE_CHAR + 1],
   cmd2[MAX_LINE_CHAR + 1], key[MAX_LINE_CHAR + 1], value[MAX_LINE_CHAR + 1];
  Table table;
  int cmd_results, cmd_to_execute, num_of_cmds, counter;

  init_table(&table);

  /* Reading from user*/
  if (argc == 1) {
     input_stream = stdin;
  } else if (argc == 2) {
    /* Reading from file input by opening the file in read mode*/
     input_stream = fopen(argv[1], "r");

     if (input_stream == NULL) {
        fprintf(stderr, "An error has occured in opening the file\n");

        return EX_OSERR;
     }
  } else {
     fprintf(stderr, "An error has occured because of an incorrent number of inputs\n");

     return EX_USAGE;
  }

  /* Reads lines until fgets retuns EOF, which is false */
  while (fgets(line_to_parse, MAX_LINE_CHAR, input_stream)) {
     if (line_to_parse[0] != '\n') {

        cmd_to_execute = sscanf(line_to_parse, "%s", cmd);

        /* Checking for if it's an empty line or  a comment*/
        if (cmd_to_execute != 0 && cmd[0] != '#') {

          /* If the command is "insert" and all of the appropriate data is
           supplied, then this if-statement inserts the key and value into
           the table. It returns FAILURE if it failed and SUCCESS if it
           succeeded. If the appropriate data is not supplied, then the
           message "Invalid number of arguments") is printed*/
           if (!strcmp(cmd, "insert")) {

              num_of_cmds = sscanf(line_to_parse, "%s %s %s", cmd, key, value);

              if (num_of_cmds == 3) {

                 cmd_results = insert(&table, key, value);

                 printf("Insertion of %s => %s ", key, value)
                 ;
                 if (cmd_results == FAILURE) {
                    printf("failed.\n");

                 } else if (cmd_results == SUCCESS) {
                    printf("succeeded.\n");

                 }
              } else {
                 fprintf(stderr, "Invalid number of arguments.\n");

                 return EX_DATAERR;
              }
           }

           /* If the command is "serch" and all of the appropriate data is
            supplied, this if-statement searches for the key and
            returns FAILURE if it failed or SUCCESS if it succeeded. It also
            displays the value associated with the key if it succeeded. If
            the appropriate data is not supplied, then the message "Invalid
            number of arguments") is printed*/
           else if (!strcmp(cmd, "search")) {

              num_of_cmds = sscanf(line_to_parse, "%s %s", cmd, key);

              if (num_of_cmds == 2) {

                 cmd_results = search(&table, key, value);

                 printf("Search for %s ", key);
                 if (cmd_results == FAILURE) {
                    printf("failed.\n");

                 } else if (cmd_results == SUCCESS) {
                    printf("succeeded (%s).\n", value);

                 }
              } else {
                 fprintf(stderr, "Invalid number of arguments.\n");

                 return EX_DATAERR;
              }
           }

           /* If the command is "delete" and all of the appropriate data is
            supplied, this if-statement deletes for the key and returns
            FAILURE if it failed or SUCCESS if it succeeded. If the
            appropriate data is not supplied, then the message "Invalid
            number of arguments") is printed*/
           else if (!strcmp(cmd, "delete")) {

              num_of_cmds = sscanf(line_to_parse, "%s %s", cmd, key);

              if ((num_of_cmds == 2)) {

                 cmd_results = delete(&table, key);

                 printf("Deletion of %s ", key);
                 if (cmd_results == FAILURE) {
                    printf("failed.\n");

                 } else if (cmd_results == SUCCESS) {
                    printf("succeeded.\n");

                 }
              } else {
                 fprintf(stderr, "Invalid number of arguments.\n");

                 return EX_DATAERR;
              }
           }

           /* If the command is "reset", the table is reset*/
           else if (!strcmp(cmd, "reset")) {

              reset_table(&table);

              printf("Table reset.\n");
           }

           /* If the command is "display", it scans again to see if the user
           want to display the key count or the table. It then prints out
           whichever was asked for. If the appropriate data is not supplied,
           then the message "Invalid number of arguments") is printed*/
           else if (!strcmp(cmd, "display")) {

              num_of_cmds = sscanf(line_to_parse, "%s %s", cmd, cmd2);

              if ((num_of_cmds == 2)) {

                 if (!strcmp(cmd2, "key_count")) {

                    printf("Key count: %d\n", table.key_ct);

                 } else if (!strcmp(cmd2, "table")) {

                    for (counter = 0; counter < NUM_BUCKETS; counter++) {
                      printf("Bucket %d: ", counter);

                       if (table.buckets[counter].state == EMPTY) {
                          printf("EMPTY\n");
                       } else if (table.buckets[counter].state == DELETED) {
                          printf("DELETED\n");
                       } else if (table.buckets[counter].state == FULL) {
                          printf("FULL (%s => %s)\n",
                                 table.buckets[counter].data.key,
                                 table.buckets[counter].data.value);
                       }
                    }
                 }
              } else {
                 fprintf(stderr, "Invalid number of arguments.\n");

                 return EX_DATAERR;
              }
           }
        }
     }
  }
  fclose(input_stream);

  return 0;
}
