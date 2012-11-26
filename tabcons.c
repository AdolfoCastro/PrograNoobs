struct tabCons{
	int constante;
	int direccionConstante;
	struct tabCons *nextPtr;
};
typedef struct tabCons TabCons;
typedef TabCons *TabConsPtr;

/*******************************Prototypes*************************/
void insertTabCons( TabConsPtr *sPtr, int consEnt, int dirCons);
int isemptyTabCons( TabConsPtr sPtr );
void printTabCons( TabConsPtr currentPtr );
void escribeTabCons(TabConsPtr currentPtr);

void insertTabCons( TabConsPtr *sPtr, int consEnt, int dirCons){
	{ 
   TabConsPtr newPtr; // pointer to new node
   TabConsPtr previousPtr; // pointer to previous node in list
   TabConsPtr currentPtr; // pointer to current node in list
   
   newPtr = malloc(sizeof(Cuadruplos)); // create node

   if ( newPtr != NULL ) { // is space available
   	newPtr->constante = consEnt;
   	newPtr->direccionConstante = dirCons;
   	
    newPtr->nextPtr = NULL; // node does not link to another node

      previousPtr = NULL;
      currentPtr = *sPtr;

      // insertCuadruplo new node at beginning of list
      if ( previousPtr == NULL ) { 
      	newPtr->nextPtr = *sPtr;
      	*sPtr = newPtr;
      } // end if
   } // end if
   else {
   	printf( "not insertCuadruploed. No memory available.\n");
   } // end else
} // end function insertCuadruplo
}
int isemptyTabCons( TabConsPtr sPtr ){
	return sPtr == NULL;
}
void printTabCons( TabConsPtr currentPtr ){
	printf("%s\n","Tabla de constantes" );
   // if list is empty
	if ( isemptyTabCons(currentPtr) ) {
		puts( "List is empty.\n" );
   } // end if
   else {
      // while not the end of the list
   	while ( currentPtr != NULL ) { 
   		printf( "%d - %d\n", currentPtr->constante, currentPtr->direccionConstante);
   		currentPtr = currentPtr->nextPtr;   
      } // end while

      //puts( "NULL\n" );
   } // end else
}
void escribeTabCons(TabConsPtr currentPtr){
   FILE *archivo;/*El manejador de archivo*/
     archivo=fopen("codigointermedio.txt", "a");
     if(archivo==NULL)/*So no lo logramos abrir, salimos*/
         exit(EXIT_FAILURE);
      fprintf(archivo, "-\n");
   if ( isemptyTabCons(currentPtr) ) {
      puts( "List is empty.\n" );
   } // end if
   else {
      // while not the end of the list
      while ( currentPtr != NULL ) { 
         fprintf(archivo, "%d-%d\n", currentPtr->direccionConstante, currentPtr->constante);/*Escribimos en el archivo*/
         currentPtr = currentPtr->nextPtr;   
      } // end while
      //puts( "NULL\n" );
   } // end else
   fclose(archivo);/*Cerramos el archivo*/
}