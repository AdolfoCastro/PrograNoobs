#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// self-referential structure                     
struct stackNode {                                   
   int data; // define data as an int             
   struct stackNode *nextPtr; // stackNode pointer
}; // end structure stackNode                     

typedef struct stackNode StackNode; // synonym for struct stackNode
typedef StackNode *StackNodePtr; // synonym for StackNode*

// prototypes
void push( StackNodePtr *topPtr, int info );
int pop( StackNodePtr *topPtr );
int isEmpty( StackNodePtr topPtr );
void printStack( StackNodePtr currentPtr );
void instructions( void );

StackNodePtr PilaO;
StackNodePtr POper;
StackNodePtr PTipos;
StackNodePtr Saltos;

// insert a node at the stack top
void push( StackNodePtr *topPtr, int info ){ 
   StackNodePtr newPtr; // pointer to new node

   newPtr = malloc( sizeof( StackNode ) );

   // insert the node at stack top
   if ( newPtr != NULL ) {           
      newPtr->data = info;           
      newPtr->nextPtr = *topPtr;     
      *topPtr = newPtr;              
   } // end if                    
   else { // no space available
      printf( "%d not inserted. No memory available.\n", info );
   } // end else
   } // end function push

// remove a node from the stack top
int pop( StackNodePtr *topPtr ){ 
   StackNodePtr tempPtr; // temporary node pointer
   int popValue; // node value

   tempPtr = *topPtr;             
   popValue = ( *topPtr )->data;  
   *topPtr = ( *topPtr )->nextPtr;
   free( tempPtr );               
   return popValue;
} // end function pop

// print the stack
void printStack( StackNodePtr currentPtr ){ 
   // if stack is empty
   if ( currentPtr == NULL ) {
      puts( "The stack is empty.\n" );
   } // end if
   else { 
      puts( "The stack is:" );

      // while not the end of the stack
      while ( currentPtr != NULL ) { 
         printf( "%d --> ", currentPtr->data );
         currentPtr = currentPtr->nextPtr;
      } // end while

      puts( "NULL\n" );
   } // end else
} // end function printList

// return 1 if the stack is empty, 0 otherwise
int isEmpty( StackNodePtr topPtr ){ 
   return topPtr == NULL;
} // end function isEmpty