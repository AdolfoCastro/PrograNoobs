/*****************
 * Declaraciones *
 ****************/ 

%{
	#include <stdio.h>
	#include <stdlib.h>
	#include "sintactico.tab.h"	
	
	int linea=0;
%}
LETRAm		[a-z]
LETRAM		[A-Z]
LETRAS		[A-Za-z]
DIGITO 		[0-9]
FLOTANTE 	{DIGITO}+("."{DIGITO}+)?
INTEGER 	{DIGITO}+?
ID 			{LETRAM}({LETRAm}+)?
NVAR 		{LETRAm}({LETRAS}+)?
STRING 		{LETRAS}+?
BOOLEAN 	(TRUE|FALSE)?
%%


 /*********************
  * Reglas y Acciones *
 **********************/

"program" 	{return(PROG);}
"var"	{return(VAR);}
"new"	{return(NEW);}
"list"	{return(LIST);}

"print"		{return(PRINT);}
"function"	{return(FUNC);}
"Main"		{return(MAIN);}
"read" 		{return(READ);}
"int"		{return(INT);}
"float"		{return(FLOAT);}
"bool"		{return(BOOL);}
"string"	{return(STR);}

"if"	{return(IF);}
"else"	{return(ELSE);}
"while"	{return(WHILE);}

{INTEGER} 	{yylval.numero=atoi(yytext); return(CINT);}
{BOOLEAN} 	{yylval.texto=strdup(yytext); return(CBOOL);}
{FLOTANTE} 	{yylval.real=atof(yytext); return(CFLOAT);}
{ID} 		{yylval.texto=strdup(yytext); return(ID);}
{NVAR} 		{yylval.texto=strdup(yytext); return(NVAR);}
{STRING} 	{yylval.texto=strdup(yytext); return(CSTR);}


"(" 	{return(PARA);}
")" 	{return(PARC);}
"," 	{return(COMA);}
"=" 	{return(IGUAL);}
"{" 	{return(LLAVEA);}
"}" 	{return(LLAVEC);}
";" 	{return(PTCM);}
":"		{return(DOSP);}
">" 	{return(MAYOR);}
"<"		{return(MENOR);}
"<>"	{return(DIFE);}
"+"		{return(MAS);}
"-"		{return(MENOS);}
"*"		{return(ASTE);}
"/"		{return(SLASH);}
"&"		{return(AND);}
"|"		{return(OR);}
"'"		{return(COMILLA);}

"\n" 	{linea++;}
[\t\r\f] {}
" "		{}
 
.	{printf("Error lexico en linea %d \n",linea);}
%%
/*********************
 * Codigo de Usuario *
*********************/



