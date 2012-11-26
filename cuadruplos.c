struct cuadruplos{
	int operacion;
	int dirOperando2;
	int dirOperando1;
	int dirResultado;
	struct cuadruplos *nextPtr;
};
typedef struct cuadruplos Cuadruplos;
typedef Cuadruplos *CuadruplosPtr;
int contSaltos;

/*******************************Prototypes*************************/
void insertCuadruplos( CuadruplosPtr *sPtr, int oper, int Operando1,int Operando2,int Resultado);
int isEmtpyCuadruplos( CuadruplosPtr sPtr );
void printCuadruplos( CuadruplosPtr currentPtr );
void escribeCuadruplos(CuadruplosPtr currentPtr);

void insertCuadruplos( CuadruplosPtr *sPtr, int oper, int Operando1,int Operando2,int Resultado){
	{ 
   CuadruplosPtr newPtr; // pointer to new node
   CuadruplosPtr previousPtr; // pointer to previous node in list
   CuadruplosPtr currentPtr; // pointer to current node in list
   
   newPtr = malloc(sizeof(Cuadruplos)); // create node

   if ( newPtr != NULL ) { // is space available
   	newPtr->operacion = oper;
   	newPtr->dirOperando1 = Operando1;
   	newPtr->dirOperando2 = Operando2;
   	newPtr->dirResultado = Resultado;
      newPtr->nextPtr = NULL; // node does not link to another node

      previousPtr = NULL;
      currentPtr = *sPtr;
      contSaltos++;

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
int isEmtpyCuadruplos( CuadruplosPtr sPtr ){
	return sPtr == NULL;
}
void printCuadruplos( CuadruplosPtr currentPtr ){
	printf("%s\n","Cuadruplos" );
   // if list is empty
	if ( isEmtpyCuadruplos(currentPtr) ) {
		puts( "List is empty.\n" );
   } // end if
   else {
      // while not the end of the list
   	while ( currentPtr != NULL ) { 
   		printf( "%d - %d - %d - %d \n", currentPtr->operacion, currentPtr->dirOperando1, currentPtr->dirOperando2, currentPtr->dirResultado);
   		currentPtr = currentPtr->nextPtr;   
      } // end while

      //puts( "NULL\n" );
   } // end else
   //printf("%d\n",contSaltos );
}
void escribeCuadruplos(CuadruplosPtr currentPtr){
   FILE *archivo;/*El manejador de archivo*/
     archivo=fopen("codigointermedio.txt", "a");
     if(archivo==NULL)/*So no lo logramos abrir, salimos*/
         exit(EXIT_FAILURE);
   if ( isEmtpyCuadruplos(currentPtr) ) {
      puts( "List is empty.\n" );
   } // end if
   else {
      // while not the end of the list
      while ( currentPtr != NULL ) { 
         fprintf(archivo, "%d,%d,%d,%d \n", currentPtr->operacion, currentPtr->dirOperando1, currentPtr->dirOperando2, currentPtr->dirResultado);/*Escribimos en el archivo*/
         currentPtr = currentPtr->nextPtr;
      } // end while

      //puts( "NULL\n" );
   } // end else
   fclose(archivo);/*Cerramos el archivo*/
}

