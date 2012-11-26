/*
#include <stdio.h>
#include <stdlib.h>
*/
struct tvarNodo
{
	char *nombreVariable;
	int tipoDato;
	int direccion;
	struct tvarNodo *nextPtr;
};
typedef struct tvarNodo TvarNodo;
typedef TvarNodo *TvarNodoPtr;

struct tproNodo{
   char *nombreFuncion;
   int tipoRetorno;
   int dirBase;
   struct tvarNodo *headTvarPtr;
   struct tproNodo *nextProPtr;
};
typedef struct tproNodo TproNodo;
typedef TproNodo *TproNodoPtr;

//Prototypes
void insertVar( TvarNodoPtr *sPtr, char *nombre, int tipo, int dir);
//char delete( TvarNodoPtr *sPtr, char value );
int isVarEmpty( TvarNodoPtr sPtr );
void printListTable( TvarNodoPtr currentPtr );
void insertPro(TproNodoPtr *sPtr, char *nombre, int tipo, int dir);
void printTables( TproNodoPtr currentPtr );
void existPro(TproNodoPtr currentProPtr, TproNodoPtr previousProPtr, char *nombre);
void existeVar(TvarNodoPtr currentPtr, TvarNodoPtr previousPtr, char *nombre);

void insertPro(TproNodoPtr *sPtr, char *nombre, int tipo, int dir)
{
   TproNodoPtr newProPtr;
   TproNodoPtr previousProPtr;
   TproNodoPtr currentProPtr;

   newProPtr = malloc(sizeof(TvarNodo));

   if ( newProPtr != NULL ) { // is space available
      newProPtr->nombreFuncion = nombre;
      newProPtr->tipoRetorno = tipo;
      newProPtr->dirBase = dir;
      newProPtr->headTvarPtr = malloc(sizeof(TvarNodo));
      newProPtr->nextProPtr = NULL; // node does not link to another node
      newProPtr->headTvarPtr->nombreVariable = "0";
      newProPtr->headTvarPtr->tipoDato = 0;
      newProPtr->headTvarPtr->direccion = 0;


      previousProPtr = NULL;
      currentProPtr = *sPtr;

      existPro ( currentProPtr, previousProPtr, nombre);

      // insert new node at beginning of list
      if ( previousProPtr == NULL ) { 
         newProPtr->nextProPtr = *sPtr;
         *sPtr = newProPtr;
      } // end if
   } // end if
   else {
      printf( "not inserted. No memory available.\n");
   } // end else
   //printf("%p\n",);
} // end function insert

void printTables( TproNodoPtr currentProPtr )
{ 
   printf("%s\n","Tabla de procedimientos y variables" );
   // if list is empty
   if ( isProEmpty(currentProPtr) ) {
      puts( "List is empty.\n" );
   } // end if
   else {
      // while not the end of the list
      while ( currentProPtr != NULL ) { 
         printf( "%s - %d - %d - %p \n", currentProPtr->nombreFuncion, currentProPtr->tipoRetorno, currentProPtr->dirBase, currentProPtr->headTvarPtr);
         while( currentProPtr->headTvarPtr != NULL)
         {
            //if(currentProPtr->headTvarPtr->nombreVariable != NULL){
               printf( "%s - %d - %d\n", currentProPtr->headTvarPtr->nombreVariable, currentProPtr->headTvarPtr->tipoDato, currentProPtr->headTvarPtr->direccion);
            //}
            currentProPtr->headTvarPtr = currentProPtr->headTvarPtr->nextPtr;
         }
         printf("\n");
         currentProPtr = currentProPtr->nextProPtr;   
      } // end while

      //puts( "NULL\n" );
   } // end else
} // end function printList

int isProEmpty( TproNodoPtr sPtr )
{ 
   return sPtr == NULL;
} // end function isProEmpty


void insertVar( TvarNodoPtr *sPtr, char *nombre, int tipo, int dir )
{ 
   TvarNodoPtr newPtr; // pointer to new node
   TvarNodoPtr previousPtr; // pointer to previous node in list
   TvarNodoPtr currentPtr; // pointer to current node in list
   
   newPtr = malloc(sizeof(TvarNodo)); // create node

   if ( newPtr != NULL ) { // is space available
      newPtr->nombreVariable = nombre;
      newPtr->tipoDato = tipo;
      newPtr->direccion = dir;
      newPtr->nextPtr = NULL; // node does not link to another node

      previousPtr = NULL;
      currentPtr = *sPtr;

      existeVar(currentPtr,previousPtr,nombre);

      // insert new node at beginning of list
      if ( previousPtr == NULL ) { 
         newPtr->nextPtr = *sPtr;
         *sPtr = newPtr;
      } // end if
   } // end if
   else {
      printf( "not inserted. No memory available.\n");
   } // end else
} // end function insert

// return 1 if the list is empty, 0 otherwise
int isVarEmpty( TvarNodoPtr sPtr )
{ 
   return sPtr == NULL;
} // end function isVarEmpty

/*
// print the list
void printVarTable( TvarNodoPtr currentPtr )
{ 
   // if list is empty
   if ( isVarEmpty( currentPtr ) ) {
      puts( "List is empty.\n" );
   } // end if
   else {
      // while not the end of the list
      while ( currentPtr != NULL ) { 
         printf( "%s - %d - %d\n", currentPtr->nombreVariable, currentPtr->tipoDato, currentPtr->direccion);
         currentPtr = currentPtr->nextPtr;   
      } // end while

      puts( "NULL\n" );
   } // end else
} // end function printList
*/

void existPro(TproNodoPtr currentProPtr, TproNodoPtr previousProPtr, char *nombre)
{
   TproNodoPtr existePtr;
   existePtr = currentProPtr;
   int i;
   while ( existePtr != NULL ) { 
         i = strcmp (nombre, existePtr->nombreFuncion);
         if (i == 0){
            printf("La funcion -%s- ya existe \n", nombre);
            exit (EXIT_FAILURE);
         }
         existePtr = existePtr->nextProPtr;   
      } // end while
}

void existeVar(TvarNodoPtr currentPtr, TvarNodoPtr previousPtr, char *nombre)
{
   TvarNodoPtr existePtr;
   existePtr = currentPtr;
   int i;
   while ( existePtr != NULL ) {
      i = strcmp (nombre, existePtr->nombreVariable);
      //printf("%s-%s",nombre,existePtr->nombreVariable);
      if (i == 0){
         printf("La variable -%s- ya existe \n", nombre);
         exit (EXIT_FAILURE);
      }
         existePtr = existePtr->nextPtr;   
      } // end while
}
void existeVarAsignar(TvarNodoPtr currentPtr, TvarNodoPtr previousPtr, char *nombre)
{
   TvarNodoPtr existePtr;
   existePtr = currentPtr;
   int existe=0;
   int i;
   while ( existePtr != NULL ) {
      i = strcmp (nombre, existePtr->nombreVariable);
      //printf("%s-%s",nombre,existePtr->nombreVariable);
      if (i == 0){
         existe=1;
      }
         existePtr = existePtr->nextPtr;   
   } // end while
   if (existe==0){
      printf("La variable -%s- a la que le quieres asignar un valor no existe \n", nombre);
         exit (EXIT_FAILURE);
   }
}

/*
int main (void){
	//TvarNodoPtr startVarList = NULL;
   TproNodoPtr startProList = NULL;
   insertPro(&startProList,"uno",1,1);
   insertVar(&startProList->headTvarPtr,"all",1,12);
   insertVar(&startProList->headTvarPtr,"ald",1,12);
   insertVar(&startProList->headTvarPtr,"alw",1,12);
   insertVar(&startProList->headTvarPtr,"allf",1,12);
   insertPro(&startProList,"dos",1,2);
   insertVar(&startProList->headTvarPtr,"alle",1,12);
   insertVar(&startProList->headTvarPtr,"all23",1,12);
   insertVar(&startProList->headTvarPtr,"all4",1,12);
   insertVar(&startProList->headTvarPtr,"all1",1,12);
   insertVar(&startProList->headTvarPtr,"all5",1,12);
   insertPro(&startProList,"tres",1,3);
   insertVar(&startProList->headTvarPtr,"all",1,12);
   printTables( startProList );

}*/