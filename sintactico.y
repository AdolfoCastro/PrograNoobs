%{

/**************************************************************** 
                     * Declaraciones en C *
 ****************************************************************/
//includes
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "stack.c"
#include "tvariables.c"
#include "cuadruplos.c"
#include "semantica.c"
#include "tabcons.c"

//cosas de flex y bison
extern int yylex(void);
extern char *yytext;
extern int linea;
extern FILE *yyin;
void yyerror(char *s);
//prototipos
//Expreciones
void unoExpInt(char *nombre);
void unoExpIntCons(int valor);
void unoExpFloat(char *nombre);
void unoExpFloatCons(int valor);
void unoExpStr(char *nombre);
void unoExpStrCons(int valor);
void unoExpBool(char *nombre);
void unoExpBoolCons(int valor);
void dosExp(int operando);
void tresExp(int operando);
void cuatroExp();
void cincoExp();
void seisExp();
void sieteExp();
void ochoExp();
void nueveExp(char *nombre);
void diezExp(char *nombre);
int direccionVariable(char *nombre);
//Estatuto IF ELSE
void cuadruplosComparaciones(int tipoComp);
void unoEstIf();
void dosEstIf();
void tresEstIf();



//Estatuto WHILE
//
//Escritura

//
char* itoa(int val, int base);
void EscrituraWrite(char *nombre);
//globales
int memoriaInt();
int operando;
int gltc;
int eragltc;
int estipo;
char *nomConsInt;
char *nomConsFloat;
int esSumResMulDiv;
char *nombrefuncion;
int cuentaInt =0;
int cuentaFloat=0;
int cuentaStr=0;
int cuentaBool=0;
int globalesInt=0;
int globalesFloat=0;
int globalesStr=0;
int globalesBool=0;
int mainInt=0;
int mainFloat=0;
int mainStr=0;
int mainBool=0;
char *nombreDeVariable;

int esComparacion;
int tipoComp;
//apuntadores estructuras
StackNodePtr apuntadorApOper;
TproNodoPtr startProList = NULL;
CuadruplosPtr  startCuadruplos =  NULL;
TabConsPtr startTabCons = NULL; 
%}
/*******************************************************************
                       Declaraciones de Bison *
 *******************************************************************/
%union{
  float real;
  int numero;
  char* texto;
}
%start programa;
//constantes
%token <texto> ID
%token <texto> NVAR
%token <numero> CINT
%token <real> CFLOAT
%token <texto> CSTR
%token <texto> CBOOL
//palabras reservadas
%token PROG
%token VAR
%token NEW
%token PRINT
%token FUNC
%token MAIN
%token READ
%token INT
%token FLOAT
%token STR
%token BOOL
%token LIST
//palabras reservadas(condicion y ciclo)
%token IF
%token ELSE
%token WHILE
//palabras reservadas(signos)
%token PARA
%token PARC
%token COMA
%token IGUAL
%token LLAVEA
%token LLAVEC
%token PTCM
%token DOSP
%token MAYOR
%token MENOR
%token DIFE
%token MAS
%token MENOS
%token ASTE
%token SLASH
%token AND
%token OR
%token COMILLA
%%
/*****************************************************************
                   * Reglas Gramaticales *
 *****************************************************************/
/**********programa*************/
programa:PROG ID LLAVEA {gltc=1;}{insertPro(&startProList,"Global",5,1);}a
{
  globalesInt=cuentaInt;
  cuentaInt=0;
  globalesFloat=cuentaFloat;
  cuentaFloat=0;
  globalesStr=cuentaStr;
  cuentaStr=0;
  globalesBool=cuentaBool;
  cuentaBool =0;
}
 {gltc=2;} main
 {
    mainInt=cuentaInt;
    cuentaInt=0;
    mainFloat=cuentaFloat;
    cuentaFloat=0;
    mainStr=cuentaStr;
    cuentaStr=0;
    mainBool=cuentaBool;
    cuentaBool =0;
 }
b LLAVEC {printf("Programa hecho \n");};
a:vars a;
a:vacio;
b:funcion b;
b:vacio;

/**********vars*************/
vars:tipo NVAR cc c PTCM {
  if (estipo==1){
    cuentaInt++;
    unoExpInt($2);
    //printf("%s\n",$2 );
  }
  if (estipo==2){
    cuentaFloat++;
    unoExpFloat($2);
    //printf("%s\n",$2 );
  }
  if (estipo==3){
    cuentaStr++;
    unoExpStr($2);
    //printf("%s\n",$2 );
  }
  if (estipo==4){
    cuentaBool++;
    unoExpBool($2);
    //printf("%s\n",$2 );
  }
};
c:IGUAL d;
c:vacio;
cc:COMA NVAR cc{
  if (estipo==1){
    unoExpInt($2);
    //printf("%s\n",$2 );
  }
  if (estipo==2){
    unoExpFloat($2);
    //printf("%s\n",$2 );
  }
  if (estipo==3){
    unoExpStr($2);
    //printf("%s\n",$2 );
  }
  if (estipo==4){
    unoExpBool($2);
    //printf("%s\n",$2 );
  }
};
cc:vacio;

d:NEW estr;
d:COMILLA CSTR COMILLA;
d:CINT;
d:CFLOAT;

/**********tipo*************/
tipo:INT{estipo=1;};
tipo:FLOAT{estipo=2;};
tipo:STR{estipo=3;};  
tipo:BOOL{estipo=4;};

/**********estr************/
estr: list;

/**********list*************/
list:LIST ee;
e:COMA cons e;
e:vacio;
ee:PARA cons e PARC;
ee:vacio;

/**********bloque*************/
bloque:f;
f:estatuto f;
f:vacio;

/**********estatuto*************/
estatuto:condicion;
estatuto:ciclo;
estatuto:lectura;
estatuto:escritura;
estatuto:asignacion;
estatuto:llamada;
estatuto:vars;

/**********main****************/
main: FUNC MAIN {insertPro(&startProList,"main",estipo,1);}PARA h PARC LLAVEA i LLAVEC {/*printf("funcion \n");*/};

/**********funcion*************/
funcion: FUNC tipo ID {insertPro(&startProList,$3,estipo,1);}PARA h PARC LLAVEA i LLAVEC {/*printf("funcion \n");*/};
h:tipo ID j;
h:;
i:bloque;
i:;
j:COMA h;

/**********asignacion*************/
asignacion:NVAR{existeVarAsignar(startProList->headTvarPtr,startProList->headTvarPtr->nextPtr,$1);} {nombreDeVariable=$1;} IGUAL{ochoExp(8);} m PTCM;
//m:clist;
m:expresion{nueveExp(nombreDeVariable);};
m:NVAR;
m:cons;

/**********condicion*************/
condicion:{eragltc=gltc; gltc=3; }IF PARA k PARC{unoEstIf();} LLAVEA bloque LLAVEC l{gltc=eragltc};
k:expresion k;
k:vacio;
l:ELSE{dosEstIf();} LLAVEA bloque LLAVEC{/*tresEstIf();*/};
l:vacio;



/**********escritura*************/
escritura:PRINT PARA n PARC PTCM;
n:NVAR {EscrituraWrite($1);}o;
n:expresion o;
n: COMILLA CSTR COMILLA o;
o:COMA n;
o:vacio;

/**********lectura*************/
lectura:READ PARA tipo PARC PTCM;

/**********ciclo*************/
ciclo:{eragltc=gltc; gltc=3;}WHILE PARA expresion PARC LLAVEA bloque LLAVEC{gltc=eragltc};

/**********expresion*************/
expresion: exp p;
p:easignacion exp{if (esComparacion==1){
  cuadruplosComparaciones(tipoComp);
}};
p:vacio;

/**********llamada*************/
llamada:ID PARA q PARC PTCM;
q:exp r;
q:vacio;
r:COMA q;
r:vacio;

/**********exp*************/
exp:T s t;

s:MAS{dosExp(1);};
s:MENOS{dosExp(2);};
s:vacio;
t:T{cuatroExp();}s t;
t:vacio;

T: F u v;
u:ASTE{tresExp(3);};
u:SLASH{tresExp(4);};
u:vacio;
v: F{cincoExp();}u v;
v:vacio;

F:w;
w:PARA{seisExp();} exp PARC{sieteExp();};
w:{eragltc=gltc; gltc=4;} z {gltc=eragltc;};
z:NVAR{
  diezExp($1);

};
z:CINT{unoExpIntCons($1);};
z:CFLOAT{unoExpFloatCons($1);};
z:COMILLA CSTR COMILLA{/*unoExpStrCons($2);*/};


/**********easignacion*************/
easignacion:IGUAL IGUAL{esComparacion=1;tipoComp=11;};
easignacion:MAYOR{esComparacion=1;tipoComp=12;};
easignacion:MENOR{esComparacion=1;tipoComp=13;};
easignacion:DIFE{esComparacion=1;tipoComp=14;};
/**********ologico*************/
ologico:AND;
ologico:OR;

/**********clist**************/
clist:PARA cons aa PARC;
aa:COMA cons aa;
aa: vacio;

/***********cons**********/
cons:ID;
cons:CINT;
cons:CFLOAT;
cons:COMILLA CSTR COMILLA;

/**********vacio*************/
vacio:;

%%
/**************************************************************************
                      * Codigo C Adicional *
 **************************************************************************/
/**************************************************************************
                   *Generacion de codigo intermedio*
***************************************************************************/
/*******************globales********************/
int contEntGlo=0;
int contFlotGlo=1000;
int contStrGlo=2000;
int contBoolGlo=3000;
//int contSaltosGlo=4000;
/*******************Locales********************/
int contEntLoc=5000;
int contFlotLoc=6000;
int contStrLoc=7000;
int contBoolLoc=8000;
//int Loc=9000;
/*******************Temporales*****************/
int contEntTmp=10000;
int contFlotTmp=11000;
int contStrTmp=12000;
int contBoolTmp=13000;
//int contSaltosTmp=14000;
/*******************Constantes*****************/
int contEntCons=15000;
int contFlotCons=16000;
int contStrCons=17000;
int contBoolCons=18000;
//int contSaltosCons=19000;

void unoExpInt(char *nombre){
  //printf("%d\n",vEntera );
  if (gltc==1){
    push (&PilaO,contEntGlo);
    push(&PTipos,1);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contEntGlo);
    contEntGlo++;
  }
  if (gltc==2){
    push (&PilaO,contEntLoc);
    push(&PTipos,1);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contEntLoc);
    contEntLoc++;
  }
  if (gltc==3){
    push (&PilaO,contEntTmp);
    push(&PTipos,1);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contEntTmp);
    contEntTmp++;
  }
}
void unoExpIntCons(int valor){
  char buffer [50];
  if (gltc==4){
    push (&PilaO,contEntCons);
    push(&PTipos,1);
    insertTabCons (&startTabCons,valor,contEntCons);
    contEntCons++;
  }
}
void unoExpFloat(char *nombre){
  //printf("%f\n",vFlotante );
  if (gltc==1){
    push(&PilaO,contFlotGlo);
    push(&PTipos,2);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contFlotGlo);
    contFlotGlo++;
  }
  if (gltc==2){
    push(&PilaO,contFlotLoc);
    push(&PTipos,2);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contFlotLoc);
    contFlotLoc++;
  }
  if (gltc==3){
    push(&PilaO,contFlotTmp);
    push(&PTipos,2);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contFlotTmp);
    contFlotTmp++;
  }
}
void unoExpFloatCons(int valor){
  if (gltc==4){
    push(&PilaO,contFlotCons);
    push(&PTipos,2);
    insertTabCons (&startTabCons,valor,contFlotCons);
    contFlotCons++;
  }
}
void unoExpStr(char *nombre){
  if (gltc==1){
    push (&PilaO, contStrGlo);
    push(&PTipos,3);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contStrGlo);
    contStrGlo++;
  }
  if (gltc==2){
    push (&PilaO, contStrLoc);
    push(&PTipos,3);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contStrLoc);
    contStrLoc++;
  }
  if (gltc==3){
    push (&PilaO, contStrTmp);
    push(&PTipos,3);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contStrTmp);
    contStrTmp++;
  }
}
void unoExpStrCons(int valor){
  if (gltc==4){
    push (&PilaO, contStrCons);
    push(&PTipos,3);
    //insertTabCons (&startTabCons,valor,contStrCons);
    contStrCons++;
  }
}
void unoExpBool(char *nombre){
  if (gltc==1){
    push (&PilaO, contBoolGlo);
    push(&PTipos,4);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contBoolGlo);
    contBoolGlo++;
  }
    if (gltc==2){
    push (&PilaO, contBoolLoc);
    push(&PTipos,4);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contBoolLoc);
    contBoolLoc++;
  }
  if (gltc==3){
    push (&PilaO, contBoolTmp);
    push(&PTipos,4);
    insertVar(&startProList->headTvarPtr,nombre,estipo,contBoolTmp);
    contBoolTmp++;
  }
}
void unoExpBoolCons(int valor){
  if (gltc==4){
    push (&PilaO, contBoolCons);
    push(&PTipos,4);
    //insertTabCons (&startTabCons,valor,contBoolCons);
    contBoolCons++;
  }
}
void dosExp(int operando){
  switch (operando){
    case 1:
      push(&POper, 1);
      break;
    case 2:
      push(&POper, 2);
      break;
  }
}
void tresExp(int operando){
  switch (operando){
    case 3:
      push(&POper, 3);
      break;
    case 4:
      push(&POper, 4);
      break;
  }
}
void cuatroExp(){
  int tipoRes;
  int resultado;
  if ( POper == NULL ) {
      //puts( "The stack is empty.\n" );
   } // end if
  else { 
    esSumResMulDiv = POper->data;
    if ( esSumResMulDiv == 1|| esSumResMulDiv == 2){
      int tipo1=PTipos->data;
      pop ( &PTipos );
      int tipo2=PTipos->data;
      pop ( &PTipos );
      if(tipo1==1){
        tipo1=0;
      }else if(tipo1==2){
        tipo1=2;
      }else if(tipo1==3){
        tipo1=5;
      }else if(tipo1==4){
        tipo1=4;
      }
      if(tipo2==1){
        tipo2=0;
      }else if(tipo2==2){
        tipo2=2;
      }else if(tipo2==3){
        tipo2=5;
      }else if(tipo2==4){
        tipo2=4;
      }
      
      if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"x")==0){
        printf("%s\n","operacion no valida" );
      }else{
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"i")==0){
          tipoRes=1;
         }
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"f")==0){
          tipoRes=2;
         }
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"s")==0){
          tipoRes=3;
         }
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"b")==0){
          tipoRes=4;
         }
      }
      //printf ("%d ", POper->data);
      int operacion=POper->data;
      pop(&POper);
      //printf("%d ", PilaO->data);
      int operando2 = PilaO->data;
      pop(&PilaO);
      //printf("%d ", PilaO->data);
      int operando1 = PilaO->data;
      pop(&PilaO);
      push(&PTipos,tipoRes);

      if(tipoRes == 1){
        push(&PilaO, contEntTmp);
        //printf("%d\n", contEntTmp );
        resultado = contEntTmp;
        contEntTmp++;
      }
      if(tipoRes == 2){
        push(&PilaO, contFlotTmp);
        //printf("%d\n", contFlotTmp );
        resultado = contFlotTmp;
        contFlotTmp++;
      }
      if(tipoRes == 3){
        push(&PilaO, contStrTmp);
        //printf("%d\n", contStrTmp );
        resultado = contStrTmp;
        contStrTmp++;
      }
      if(tipoRes == 4){
        push(&PilaO, contBoolTmp);
        //printf("%d\n", contBoolTmp );
        resultado = contBoolTmp;
        contBoolTmp++;
      }
      insertCuadruplos( &startCuadruplos, operacion, operando1, operando2, resultado);
    }
  }
}
void cincoExp(){
  int tipoRes;
  int resultado;
  if ( POper == NULL ) {
      //puts( "The stack is empty.\n" );
   } // end if
  else { 
    esSumResMulDiv = POper->data;
    if ( esSumResMulDiv == 3 || esSumResMulDiv == 4){
      int tipo1 = PTipos->data;
      pop ( &PTipos );
      int tipo2 = PTipos->data;
      pop ( &PTipos );

      if(tipo1==1){
        tipo1=0;
      }else if(tipo1==2){
        tipo1=2;
      }else if(tipo1==3){
        tipo1=5;
      }else if(tipo1==4){
        tipo1=4;
      }
      if(tipo2==1){
        tipo2=0;
      }else if(tipo2==2){
        tipo2=2;
      }else if(tipo2==3){
        tipo2=5;
      }else if(tipo2==4){
        tipo2=4;
      }
      if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"x")==0){
        printf("%s\n","operacion no valida" );
      }else{
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"i")==0){
          tipoRes=1;
         }
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"f")==0){
          tipoRes=2;
         }
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"s")==0){
          tipoRes=3;
         }
         if(strcmp(cubo[tipo1][tipo2][esSumResMulDiv],"b")==0){
          tipoRes=4;
         }
      }

      printf ("%d ", POper->data);
      int operacion=POper->data;
      pop(&POper);
      printf("%d ", PilaO->data);
      int operando2 = PilaO->data;
      pop(&PilaO);
      printf("%d ", PilaO->data);
      int operando1 = PilaO->data;
      pop(&PilaO);
      push(&PTipos,tipoRes);

      if(tipoRes == 1){
        push(&PilaO, contEntTmp);
        printf("%d\n", contEntTmp );
        resultado = contEntTmp;
        contEntTmp++;
      }
      if(tipoRes == 2){
        push(&PilaO, contFlotTmp);
        printf("%d\n", contFlotTmp );
        resultado = contFlotTmp;
        contFlotTmp++;
      }
      if(tipoRes == 3){
        push(&PilaO, contStrTmp);
        printf("%d\n", contStrTmp );
        resultado = contStrTmp;
        contStrTmp++;
      }
      if(tipoRes == 4){
        push(&PilaO, contBoolTmp);
        printf("%d\n", contBoolTmp );
        resultado = contBoolTmp;
        contBoolTmp++;
      }
      insertCuadruplos( &startCuadruplos, operacion, operando1, operando2, resultado);
    }
  }
}
void seisExp(){
  push(&POper, 9);
}
void sieteExp(){
  pop(&POper);
}
void ochoExp(eAsigna){
  switch (eAsigna){
    case 5:
      push(&POper, 5);
      break;
    case 6:
      push(&POper, 6);
      break;
    case 7:
      push(&POper, 7);
      break;
    case 8:
      push(&POper, 8);
      break;
  }
}
void nueveExp(char *nombre){
 if ( POper == NULL ) {
      //puts( "The stack is empty.\n" );
 }else { 
  esSumResMulDiv = POper->data;
    if ( esSumResMulDiv == 5 || esSumResMulDiv == 6 || esSumResMulDiv == 7|| esSumResMulDiv == 8 ){
      pop ( &PTipos );
      //printf ("%d ", POper->data);
      int operacion=POper->data;
      pop(&POper);
      //printf("%d ", PilaO->data);
      int operando1 = PilaO->data;
      pop(&PilaO);
      //printf("%d \n", PilaO->data);
      int resultado = direccionVariable(nombreDeVariable);
      pop(&PilaO);
      int operando2=0;
      insertCuadruplos( &startCuadruplos, operacion, operando1, operando2,resultado);
    }
  }
}
int direccionVariable(char *nombre){
  //revisa que los nombres de las variables y funciones no existan 
    TvarNodoPtr existePtr;
    existePtr = startProList->headTvarPtr;
    int esta = 0;
    int i;
    int direccion;

    while ( existePtr != NULL ) {
      i = strcmp (nombre, existePtr->nombreVariable);
       //printf("%s-%s",nombre,existePtr->nombreVariable);
      if (i == 0){
        direccion = existePtr->direccion;
        printf ("%d\n",direccion);
      }
      existePtr = existePtr->nextPtr;   
      } // end while
      return direccion;
}
void diezExp(char *nombre){
  //revisa que los nombres de las variables y funciones no existan 
    TvarNodoPtr existePtr;
    existePtr = startProList->headTvarPtr;
    int esta = 0;
    int i;

    while ( existePtr != NULL ) {
      i = strcmp (nombre, existePtr->nombreVariable);
       //printf("%s-%s",nombre,existePtr->nombreVariable);
      if (i == 0){
         push ( &PilaO, existePtr->direccion);
         esta = 1;
      }
      existePtr = existePtr->nextPtr;   
      } // end while
    if(esta != 1){
      printf("la variable '%s' no existe  %s\n",nombre,existePtr->nombreVariable);
      exit(EXIT_FAILURE);
    }
}
void cuadruplosComparaciones(int tipoComp){
  int operacion;
  int operando1;
  int operando2;
  int resultado;
  operacion=tipoComp;
  operando2=PilaO->data;
  pop(&PilaO);
  operando1=PilaO->data;
  pop(&PilaO);
  resultado = contBoolTmp;
  push (&PilaO, contBoolTmp);
  contBoolTmp++;
  insertCuadruplos( &startCuadruplos, operacion, operando1, operando2, resultado);
  esComparacion=0;
}
void EscrituraWrite(char *nombre){
  int direccion;
  direccion = direccionVariable(nombre);
  insertCuadruplos(&startCuadruplos,30,direccion,0,0);
}
void unoEstIf(){
  //21 gotof
  int guarda;
  insertCuadruplos(&startCuadruplos, 21,PilaO->data,0,0);
  guarda = PilaO->data;
  pop(&PilaO);
  pop(&PilaO);
  push(&PilaO,guarda);
  push (&Saltos,contSaltos-1);
}
void dosEstIf(){
  int diferenciaCuadruplos;
  CuadruplosPtr memoriaCuadruplo;
  memoriaCuadruplo = startCuadruplos->nextPtr;
  insertCuadruplos(&startCuadruplos,20,0,0,0);  
  diferenciaCuadruplos = contSaltos-1-Saltos->data;
  memoriaCuadruplo=startCuadruplos->nextPtr;

  for (int i=0;i<diferenciaCuadruplos-1;i++){
    memoriaCuadruplo=memoriaCuadruplo->nextPtr;
  }
  memoriaCuadruplo->dirResultado = contSaltos-1;
  pop(&Saltos);

  push (&Saltos,contSaltos-1);
}
void tresEstIf(){
  int diferenciaCuadruplos;
  CuadruplosPtr memoriaCuadruplo;
  memoriaCuadruplo = startCuadruplos->nextPtr;
  diferenciaCuadruplos=contSaltos-1-Saltos->data;
  printf("%d----\n",diferenciaCuadruplos);
  memoriaCuadruplo = startCuadruplos->nextPtr;
  for (int i=0;i<diferenciaCuadruplos-1;i++){
    memoriaCuadruplo=memoriaCuadruplo->nextPtr;
  }

  memoriaCuadruplo->dirResultado = contSaltos-1;

  printf("%d????%d\n",Saltos->data,contSaltos-1 );
  pop(&Saltos);
}
char* itoa(int val, int base){
  
  static char buf[32] = {0};
  
  int i = 30;
  
  for(; val && i ; --i, val /= base)
  
    buf[i] = "0123456789abcdef"[val % base];
  
  return &buf[i+1];
  
}


void yyerror(char *s){
  printf("Error sintactico %s \n",s);
}
/**************** main ****************/
int main(int argc,char **argv){
  PilaO = NULL;// points to stack top
  POper = NULL;
  PTipos = NULL;
  Saltos = NULL;


  int value; // int input by user
  
  if (argc>1)
    yyin=fopen(argv[1],"rt");
  else
    //yyin=stdin;
    yyin=fopen("Programa.ac","rt");

  yyparse();
  escribeCuadruplos( startCuadruplos );
  escribeTabCons(startTabCons);
  FILE *archivo;/*El manejador de archivo*/
     archivo=fopen("codigointermedio.txt", "a");
     if(archivo==NULL)/*So no lo logramos abrir, salimos*/
         exit(EXIT_FAILURE);
      fprintf(archivo, "-\n");
      fprintf(archivo, "%d-%d-%d-%d\n",globalesInt,globalesFloat,globalesStr,globalesBool);
      fprintf(archivo,"Main-0-%d-%d-%d-%d-%d-%d-%d-%d",mainInt,mainFloat,mainStr,mainBool,contEntTmp-10000,contFlotTmp-11000,contStrTmp-12000,contBoolTmp-13000);
      fclose(archivo);
  //impreciones de prueba
  printf("PilaO \n");
  printStack( PilaO );
  printf("POper \n");
  printStack( POper );
  printf("PTipos \n");
  printStack( PTipos );
  printf("Saltos \n");
  printStack( Saltos );
  printTables( startProList );
  printCuadruplos ( startCuadruplos );
  printTabCons( startTabCons);
  printf("%d\n",contSaltos );
  return 0;
  
}
