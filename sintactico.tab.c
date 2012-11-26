/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.3"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Using locations.  */
#define YYLSP_NEEDED 0



/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     ID = 258,
     NVAR = 259,
     CINT = 260,
     CFLOAT = 261,
     CSTR = 262,
     CBOOL = 263,
     PROG = 264,
     VAR = 265,
     NEW = 266,
     PRINT = 267,
     FUNC = 268,
     MAIN = 269,
     READ = 270,
     INT = 271,
     FLOAT = 272,
     STR = 273,
     BOOL = 274,
     LIST = 275,
     IF = 276,
     ELSE = 277,
     WHILE = 278,
     PARA = 279,
     PARC = 280,
     COMA = 281,
     IGUAL = 282,
     LLAVEA = 283,
     LLAVEC = 284,
     PTCM = 285,
     DOSP = 286,
     MAYOR = 287,
     MENOR = 288,
     DIFE = 289,
     MAS = 290,
     MENOS = 291,
     ASTE = 292,
     SLASH = 293,
     AND = 294,
     OR = 295,
     COMILLA = 296
   };
#endif
/* Tokens.  */
#define ID 258
#define NVAR 259
#define CINT 260
#define CFLOAT 261
#define CSTR 262
#define CBOOL 263
#define PROG 264
#define VAR 265
#define NEW 266
#define PRINT 267
#define FUNC 268
#define MAIN 269
#define READ 270
#define INT 271
#define FLOAT 272
#define STR 273
#define BOOL 274
#define LIST 275
#define IF 276
#define ELSE 277
#define WHILE 278
#define PARA 279
#define PARC 280
#define COMA 281
#define IGUAL 282
#define LLAVEA 283
#define LLAVEC 284
#define PTCM 285
#define DOSP 286
#define MAYOR 287
#define MENOR 288
#define DIFE 289
#define MAS 290
#define MENOS 291
#define ASTE 292
#define SLASH 293
#define AND 294
#define OR 295
#define COMILLA 296




/* Copy the first part of user declarations.  */
#line 1 "sintactico.y"


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
void unoEstWhile();
void dosEstWhile();
void tresEstWhile();
//Escritura

//
//llamadas
void unollamada(char *nombre);
//
void tablaDeProcedimientosGlobal();
void tablaDeProcedimientosMain();
void tablaDeProcedimientosFunciones(char *nombre,int cuadruploDeEntrada);
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
int restaEnt;
int restaFlo;
int restaStr;
int restaBool;
char *nombreDeVariable;
int cuadruploDeEntrada;

int esComparacion;
int tipoComp;

//apuntadores estructuras
StackNodePtr apuntadorApOper;
TproNodoPtr startProList = NULL;
CuadruplosPtr  startCuadruplos =  NULL;
TabConsPtr startTabCons = NULL; 


/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif

#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 107 "sintactico.y"
{
  float real;
  int numero;
  char* texto;
}
/* Line 193 of yacc.c.  */
#line 288 "sintactico.tab.c"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 301 "sintactico.tab.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int i)
#else
static int
YYID (i)
    int i;
#endif
{
  return i;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss;
  YYSTYPE yyvs;
  };

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack)					\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack, Stack, yysize);				\
	Stack = &yyptr->Stack;						\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  4
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   202

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  42
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  74
/* YYNRULES -- Number of rules.  */
#define YYNRULES  121
/* YYNRULES -- Number of states.  */
#define YYNSTATES  220

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   296

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     4,     5,     6,     7,    19,    22,    24,
      27,    29,    35,    38,    40,    44,    46,    49,    53,    55,
      57,    59,    61,    63,    65,    67,    70,    74,    76,    81,
      83,    85,    88,    90,    92,    94,    96,    98,   100,   102,
     104,   105,   106,   107,   119,   120,   121,   122,   135,   139,
     140,   142,   143,   146,   147,   148,   149,   157,   159,   161,
     163,   164,   165,   176,   179,   181,   182,   188,   190,   196,
     197,   201,   204,   209,   212,   214,   220,   221,   222,   223,
     224,   236,   239,   242,   244,   245,   252,   255,   257,   260,
     262,   266,   268,   270,   272,   273,   278,   280,   284,   286,
     288,   290,   291,   296,   298,   300,   301,   306,   307,   310,
     312,   314,   316,   320,   323,   325,   327,   329,   331,   333,
     335,   339
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      43,     0,    -1,    -1,    -1,    -1,    -1,     9,     3,    28,
      44,    45,    48,    46,    47,    62,    49,    29,    -1,    50,
      48,    -1,   115,    -1,    66,    49,    -1,   115,    -1,    54,
       4,    52,    51,    30,    -1,    27,    53,    -1,   115,    -1,
      26,     4,    52,    -1,   115,    -1,    11,    55,    -1,    41,
       7,    41,    -1,     5,    -1,     6,    -1,    16,    -1,    17,
      -1,    18,    -1,    19,    -1,    56,    -1,    20,    58,    -1,
      26,   114,    57,    -1,   115,    -1,    24,   114,    57,    25,
      -1,   115,    -1,    60,    -1,    61,    60,    -1,   115,    -1,
      78,    -1,    89,    -1,    88,    -1,    84,    -1,    73,    -1,
      96,    -1,    50,    -1,    -1,    -1,    -1,    13,    14,    63,
      64,    24,    70,    25,    28,    71,    29,    65,    -1,    -1,
      -1,    -1,    13,    54,     3,    67,    68,    24,    70,    25,
      28,    71,    29,    69,    -1,    54,     3,    72,    -1,    -1,
      59,    -1,    -1,    26,    70,    -1,    -1,    -1,    -1,     4,
      74,    75,    27,    76,    77,    30,    -1,    94,    -1,     4,
      -1,   114,    -1,    -1,    -1,    79,    21,    24,    81,    25,
      80,    28,    59,    29,    82,    -1,    94,    81,    -1,   115,
      -1,    -1,    22,    83,    28,    59,    29,    -1,   115,    -1,
      12,    24,    85,    25,    30,    -1,    -1,     4,    86,    87,
      -1,    94,    87,    -1,    41,     7,    41,    87,    -1,    26,
      85,    -1,   115,    -1,    15,    24,    54,    25,    30,    -1,
      -1,    -1,    -1,    -1,    90,    23,    91,    24,    94,    25,
      28,    92,    59,    29,    93,    -1,   100,    95,    -1,   113,
     100,    -1,   115,    -1,    -1,     3,    97,    24,    98,    25,
      30,    -1,   100,    99,    -1,   115,    -1,    26,    98,    -1,
     115,    -1,   104,   101,   102,    -1,    35,    -1,    36,    -1,
     115,    -1,    -1,   104,   103,   101,   102,    -1,   115,    -1,
     108,   105,   106,    -1,    37,    -1,    38,    -1,   115,    -1,
      -1,   108,   107,   105,   106,    -1,   115,    -1,   109,    -1,
      -1,    24,   110,   100,    25,    -1,    -1,   111,   112,    -1,
       4,    -1,     5,    -1,     6,    -1,    41,     7,    41,    -1,
      27,    27,    -1,    32,    -1,    33,    -1,    34,    -1,     3,
      -1,     5,    -1,     6,    -1,    41,     7,    41,    -1,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   161,   161,   161,   162,   171,   161,   172,   173,   174,
     175,   178,   200,   201,   202,   220,   222,   223,   224,   225,
     228,   229,   230,   231,   234,   237,   238,   239,   240,   241,
     244,   245,   246,   249,   250,   251,   252,   253,   254,   255,
     257,   257,   257,   257,   260,   260,   260,   260,   261,   262,
     263,   264,   265,   268,   268,   268,   268,   270,   271,   272,
     275,   275,   275,   276,   277,   278,   278,   279,   284,   285,
     285,   286,   287,   288,   289,   292,   295,   295,   295,   295,
     295,   298,   299,   302,   305,   305,   306,   307,   308,   309,
     312,   314,   315,   316,   317,   317,   318,   320,   321,   322,
     323,   324,   324,   325,   327,   328,   328,   329,   329,   330,
     333,   334,   335,   339,   340,   341,   342,   353,   354,   355,
     356,   359
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "ID", "NVAR", "CINT", "CFLOAT", "CSTR",
  "CBOOL", "PROG", "VAR", "NEW", "PRINT", "FUNC", "MAIN", "READ", "INT",
  "FLOAT", "STR", "BOOL", "LIST", "IF", "ELSE", "WHILE", "PARA", "PARC",
  "COMA", "IGUAL", "LLAVEA", "LLAVEC", "PTCM", "DOSP", "MAYOR", "MENOR",
  "DIFE", "MAS", "MENOS", "ASTE", "SLASH", "AND", "OR", "COMILLA",
  "$accept", "programa", "@1", "@2", "@3", "@4", "a", "b", "vars", "c",
  "cc", "d", "tipo", "estr", "list", "e", "ee", "bloque", "f", "estatuto",
  "main", "@5", "@6", "@7", "funcion", "@8", "@9", "@10", "h", "i", "j",
  "asignacion", "@11", "@12", "@13", "m", "condicion", "@14", "@15", "k",
  "l", "@16", "escritura", "n", "@17", "o", "lectura", "ciclo", "@18",
  "@19", "@20", "@21", "expresion", "p", "llamada", "@22", "q", "r", "exp",
  "s", "t", "@23", "T", "u", "v", "@24", "F", "w", "@25", "@26", "z",
  "easignacion", "cons", "vacio", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    42,    44,    45,    46,    47,    43,    48,    48,    49,
      49,    50,    51,    51,    52,    52,    53,    53,    53,    53,
      54,    54,    54,    54,    55,    56,    57,    57,    58,    58,
      59,    60,    60,    61,    61,    61,    61,    61,    61,    61,
      63,    64,    65,    62,    67,    68,    69,    66,    70,    70,
      71,    71,    72,    74,    75,    76,    73,    77,    77,    77,
      79,    80,    78,    81,    81,    83,    82,    82,    84,    86,
      85,    85,    85,    87,    87,    88,    90,    91,    92,    93,
      89,    94,    95,    95,    97,    96,    98,    98,    99,    99,
     100,   101,   101,   101,   103,   102,   102,   104,   105,   105,
     105,   107,   106,   106,   108,   110,   109,   111,   109,   112,
     112,   112,   112,   113,   113,   113,   113,   114,   114,   114,
     114,   115
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     0,     0,     0,     0,    11,     2,     1,     2,
       1,     5,     2,     1,     3,     1,     2,     3,     1,     1,
       1,     1,     1,     1,     1,     2,     3,     1,     4,     1,
       1,     2,     1,     1,     1,     1,     1,     1,     1,     1,
       0,     0,     0,    11,     0,     0,     0,    12,     3,     0,
       1,     0,     2,     0,     0,     0,     7,     1,     1,     1,
       0,     0,    10,     2,     1,     0,     5,     1,     5,     0,
       3,     2,     4,     2,     1,     5,     0,     0,     0,     0,
      11,     2,     2,     1,     0,     6,     2,     1,     2,     1,
       3,     1,     1,     1,     0,     4,     1,     3,     1,     1,
       1,     0,     4,     1,     1,     0,     4,     0,     2,     1,
       1,     1,     3,     2,     1,     1,     1,     1,     1,     1,
       3,     0
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     1,     2,     3,   121,    20,    21,
      22,    23,     4,   121,     0,     8,     5,     7,   121,     0,
       0,   121,    15,     0,   121,   121,     0,     0,    13,    40,
       0,     0,   121,    10,    14,    18,    19,     0,     0,    12,
      11,    41,     0,     6,     9,   121,    16,    24,     0,     0,
      44,     0,    25,    29,    17,    49,    45,   117,   118,   119,
       0,   121,     0,     0,     0,     0,     0,     0,    27,     0,
       0,    49,   120,   121,    28,    49,    48,    51,     0,    26,
      52,    84,    53,     0,     0,    39,    50,    30,    60,     0,
      37,    33,     0,    36,    35,    34,     0,    38,    32,     0,
       0,    54,   107,     0,    31,    42,     0,    77,    51,   107,
       0,    69,   105,     0,     0,   121,   121,   121,   121,   104,
       0,     0,    43,   107,     0,     0,     0,   121,    87,    55,
     121,   107,     0,     0,   107,    71,    74,     0,   114,   115,
     116,    81,   107,    83,    91,    92,   121,    93,    98,    99,
     121,   100,   109,   110,   111,     0,   108,     0,     0,   107,
      64,   107,    46,     0,   107,    86,    89,     0,    70,     0,
     121,    68,    73,   113,    82,    90,    94,    96,    97,   101,
     103,     0,    75,    61,    63,     0,    47,    85,    88,    58,
       0,    57,    59,   106,    72,   121,   121,   112,     0,     0,
      56,   121,   121,    60,    78,    95,   102,     0,    60,   121,
       0,    65,    62,    67,    79,     0,    80,    60,     0,    66
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     6,     7,    16,    19,    12,    31,    85,    27,
      21,    39,    14,    46,    47,    67,    52,    86,    87,    88,
      24,    41,    49,   122,    32,    56,    64,   186,    63,    89,
      76,    90,   101,   110,   167,   190,    91,    92,   198,   158,
     212,   215,    93,   114,   130,   135,    94,    95,    96,   124,
     208,   216,   115,   141,    97,   100,   126,   165,   116,   146,
     175,   195,   117,   150,   178,   196,   118,   119,   131,   120,
     156,   142,    61,    98
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -165
static const yytype_int16 yypact[] =
{
      11,    10,    50,    24,  -165,  -165,  -165,    81,  -165,  -165,
    -165,  -165,  -165,    81,    61,  -165,  -165,  -165,    37,    54,
      67,    46,  -165,    63,    74,    37,     4,    58,  -165,  -165,
      81,    65,    74,  -165,  -165,  -165,  -165,    70,    94,  -165,
    -165,  -165,   101,  -165,  -165,    82,  -165,  -165,    64,    83,
    -165,    16,  -165,  -165,  -165,    81,  -165,  -165,  -165,  -165,
     107,    89,   114,    93,    95,    88,    16,    97,  -165,   105,
     106,    81,  -165,    89,  -165,    81,  -165,   109,   108,  -165,
    -165,  -165,  -165,   111,   112,  -165,  -165,  -165,    66,   113,
    -165,  -165,   116,  -165,  -165,  -165,   115,  -165,  -165,   117,
     120,  -165,    23,    81,  -165,  -165,   122,  -165,   109,    -1,
     121,  -165,  -165,   133,   124,   125,    59,    40,    42,  -165,
      55,   128,  -165,    -1,   123,   126,   129,   130,  -165,  -165,
     125,   134,   100,   131,    23,  -165,  -165,   132,  -165,  -165,
    -165,  -165,   134,  -165,  -165,  -165,    31,  -165,  -165,  -165,
      31,  -165,  -165,  -165,  -165,   143,  -165,   135,   137,    -1,
    -165,   134,  -165,   136,    -1,  -165,  -165,    27,  -165,   139,
     125,  -165,  -165,  -165,  -165,  -165,  -165,  -165,  -165,  -165,
    -165,   119,  -165,  -165,  -165,   142,  -165,  -165,  -165,  -165,
     138,  -165,  -165,  -165,  -165,    40,    42,  -165,   141,   144,
    -165,    31,    31,    66,  -165,  -165,  -165,   145,    66,   148,
     146,  -165,  -165,  -165,  -165,   149,  -165,    66,   147,  -165
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -165,  -165,  -165,  -165,  -165,  -165,   158,   150,    21,  -165,
     153,  -165,   -29,  -165,  -165,   110,  -165,  -164,    85,  -165,
    -165,  -165,  -165,  -165,  -165,  -165,  -165,  -165,   -59,    71,
    -165,  -165,  -165,  -165,  -165,  -165,  -165,  -165,  -165,    22,
    -165,  -165,  -165,    51,  -165,  -122,  -165,  -165,  -165,  -165,
    -165,  -165,  -118,  -165,  -165,  -165,    20,  -165,  -102,   -15,
     -14,  -165,  -143,   -10,   -12,  -165,  -146,  -165,  -165,  -165,
    -165,  -165,   -64,    -7
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -122
static const yytype_int16 yytable[] =
{
      15,    42,    73,   176,   179,   159,    15,   127,   168,    35,
      36,    22,    78,     3,    28,    37,    80,    33,    22,    57,
       1,    58,    59,   112,  -121,    33,    62,   111,    13,   169,
      57,   189,    58,    59,    13,  -107,  -107,  -107,    53,   207,
     174,   159,    62,   185,   210,    38,    62,   112,   194,   191,
       4,   112,     5,   218,    68,   112,   179,    60,   176,   152,
     153,   154,   127,    20,   113,    18,    68,    23,    60,    81,
      82,    25,  -107,    26,   121,   144,   145,    29,    83,   148,
     149,    84,     8,     9,    10,    11,   137,    30,    40,   -76,
      45,   138,   139,   140,    43,  -121,   155,     8,     9,    10,
      11,    48,   128,   192,    50,    54,    51,    55,   136,   143,
     147,   151,    81,    82,    65,    66,   160,    69,    70,    71,
     166,    83,    74,   136,    84,     8,     9,    10,    11,    72,
     -60,    75,   -76,    99,    77,   102,   103,   106,   107,   177,
     132,   170,   105,   180,   109,   108,   123,   161,   129,   133,
     181,   134,   160,   157,   163,   162,   164,   128,   112,   173,
     197,   171,   183,   136,   193,   182,   187,   199,   200,   203,
     211,    17,   204,   104,   209,   214,   219,   217,    34,   125,
     201,   184,    44,    79,   188,   172,   202,   205,   147,   151,
     206,     0,     0,     0,   177,   180,     0,     0,     0,     0,
       0,     0,   213
};

static const yytype_int16 yycheck[] =
{
       7,    30,    66,   146,   150,   123,    13,   109,   130,     5,
       6,    18,    71,     3,    21,    11,    75,    24,    25,     3,
       9,     5,     6,    24,    25,    32,    55,     4,     7,   131,
       3,     4,     5,     6,    13,     4,     5,     6,    45,   203,
     142,   159,    71,   161,   208,    41,    75,    24,   170,   167,
       0,    24,    28,   217,    61,    24,   202,    41,   201,     4,
       5,     6,   164,    26,    41,     4,    73,    13,    41,     3,
       4,     4,    41,    27,   103,    35,    36,    14,    12,    37,
      38,    15,    16,    17,    18,    19,    27,    13,    30,    23,
      20,    32,    33,    34,    29,    29,    41,    16,    17,    18,
      19,     7,   109,   167,     3,    41,    24,    24,   115,   116,
     117,   118,     3,     4,     7,    26,   123,     3,    25,    24,
     127,    12,    25,   130,    15,    16,    17,    18,    19,    41,
      21,    26,    23,    25,    28,    24,    24,    21,    23,   146,
       7,    41,    29,   150,    24,    28,    24,    24,    27,    25,
       7,    26,   159,    25,    25,    29,    26,   164,    24,    27,
      41,    30,    25,   170,    25,    30,    30,    25,    30,    28,
      22,    13,    28,    88,    29,    29,    29,    28,    25,   108,
     195,   159,    32,    73,   164,   134,   196,   201,   195,   196,
     202,    -1,    -1,    -1,   201,   202,    -1,    -1,    -1,    -1,
      -1,    -1,   209
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     9,    43,     3,     0,    28,    44,    45,    16,    17,
      18,    19,    48,    50,    54,   115,    46,    48,     4,    47,
      26,    52,   115,    13,    62,     4,    27,    51,   115,    14,
      13,    49,    66,   115,    52,     5,     6,    11,    41,    53,
      30,    63,    54,    29,    49,    20,    55,    56,     7,    64,
       3,    24,    58,   115,    41,    24,    67,     3,     5,     6,
      41,   114,    54,    70,    68,     7,    26,    57,   115,     3,
      25,    24,    41,   114,    25,    26,    72,    28,    70,    57,
      70,     3,     4,    12,    15,    50,    59,    60,    61,    71,
      73,    78,    79,    84,    88,    89,    90,    96,   115,    25,
      97,    74,    24,    24,    60,    29,    21,    23,    28,    24,
      75,     4,    24,    41,    85,    94,   100,   104,   108,   109,
     111,    54,    65,    24,    91,    71,    98,   100,   115,    27,
      86,   110,     7,    25,    26,    87,   115,    27,    32,    33,
      34,    95,   113,   115,    35,    36,   101,   115,    37,    38,
     105,   115,     4,     5,     6,    41,   112,    25,    81,    94,
     115,    24,    29,    25,    26,    99,   115,    76,    87,   100,
      41,    30,    85,    27,   100,   102,   104,   115,   106,   108,
     115,     7,    30,    25,    81,    94,    69,    30,    98,     4,
      77,    94,   114,    25,    87,   103,   107,    41,    80,    25,
      30,   101,   105,    28,    28,   102,   106,    59,    92,    29,
      59,    22,    82,   115,    29,    83,    93,    28,    59,    29
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */

#define YYFAIL		goto yyerrlab

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *bottom, yytype_int16 *top)
#else
static void
yy_stack_print (bottom, top)
    yytype_int16 *bottom;
    yytype_int16 *top;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; bottom <= top; ++bottom)
    YYFPRINTF (stderr, " %d", *bottom);
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      fprintf (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      fprintf (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}


/* Prevent warnings from -Wmissing-prototypes.  */

#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */



/* The look-ahead symbol.  */
int yychar;

/* The semantic value of the look-ahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
  
  int yystate;
  int yyn;
  int yyresult;
  /* Number of tokens to shift before error messages enabled.  */
  int yyerrstatus;
  /* Look-ahead token as an internal (translated) token number.  */
  int yytoken = 0;
#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

  /* Three stacks and their tools:
     `yyss': related to states,
     `yyvs': related to semantic values,
     `yyls': related to locations.

     Refer to the stacks thru separate pointers, to allow yyoverflow
     to reallocate them elsewhere.  */

  /* The state stack.  */
  yytype_int16 yyssa[YYINITDEPTH];
  yytype_int16 *yyss = yyssa;
  yytype_int16 *yyssp;

  /* The semantic value stack.  */
  YYSTYPE yyvsa[YYINITDEPTH];
  YYSTYPE *yyvs = yyvsa;
  YYSTYPE *yyvsp;



#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  YYSIZE_T yystacksize = YYINITDEPTH;

  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;


  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY;		/* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */

  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;


	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),

		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss);
	YYSTACK_RELOCATE (yyvs);

#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;


      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     look-ahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to look-ahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a look-ahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid look-ahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the look-ahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token unless it is eof.  */
  if (yychar != YYEOF)
    yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
#line 161 "sintactico.y"
    {gltc=1;;}
    break;

  case 3:
#line 161 "sintactico.y"
    {insertPro(&startProList,"Global",5,1);;}
    break;

  case 4:
#line 162 "sintactico.y"
    {
  globalesInt=cuentaInt;
  cuentaInt=0;
  globalesFloat=cuentaFloat;
  cuentaFloat=0;
  globalesStr=cuentaStr;
  cuentaStr=0;
  globalesBool=cuentaBool;
  cuentaBool =0;
;}
    break;

  case 5:
#line 171 "sintactico.y"
    {gltc=2;;}
    break;

  case 6:
#line 171 "sintactico.y"
    {printf("Programa hecho \n");;}
    break;

  case 11:
#line 178 "sintactico.y"
    {
  if (estipo==1){
    cuentaInt++;
    unoExpInt((yyvsp[(2) - (5)].texto));
    //printf("%s\n",$2 );
  }
  if (estipo==2){
    cuentaFloat++;
    unoExpFloat((yyvsp[(2) - (5)].texto));
    //printf("%s\n",$2 );
  }
  if (estipo==3){
    cuentaStr++;
    unoExpStr((yyvsp[(2) - (5)].texto));
    //printf("%s\n",$2 );
  }
  if (estipo==4){
    cuentaBool++;
    unoExpBool((yyvsp[(2) - (5)].texto));
    //printf("%s\n",$2 );
  }
;}
    break;

  case 14:
#line 202 "sintactico.y"
    {
  if (estipo==1){
    unoExpInt((yyvsp[(2) - (3)].texto));
    //printf("%s\n",$2 );
  }
  if (estipo==2){
    unoExpFloat((yyvsp[(2) - (3)].texto));
    //printf("%s\n",$2 );
  }
  if (estipo==3){
    unoExpStr((yyvsp[(2) - (3)].texto));
    //printf("%s\n",$2 );
  }
  if (estipo==4){
    unoExpBool((yyvsp[(2) - (3)].texto));
    //printf("%s\n",$2 );
  }
;}
    break;

  case 20:
#line 228 "sintactico.y"
    {estipo=1;;}
    break;

  case 21:
#line 229 "sintactico.y"
    {estipo=2;;}
    break;

  case 22:
#line 230 "sintactico.y"
    {estipo=3;;}
    break;

  case 23:
#line 231 "sintactico.y"
    {estipo=4;;}
    break;

  case 40:
#line 257 "sintactico.y"
    {tablaDeProcedimientosGlobal();;}
    break;

  case 41:
#line 257 "sintactico.y"
    {insertPro(&startProList,"main",estipo,1);;}
    break;

  case 42:
#line 257 "sintactico.y"
    {tablaDeProcedimientosMain();;}
    break;

  case 43:
#line 257 "sintactico.y"
    {/*printf("funcion \n");*/;}
    break;

  case 44:
#line 260 "sintactico.y"
    {cuadruploDeEntrada=contSaltos;;}
    break;

  case 45:
#line 260 "sintactico.y"
    {insertPro(&startProList,(yyvsp[(3) - (4)].texto),estipo,1);;}
    break;

  case 46:
#line 260 "sintactico.y"
    {tablaDeProcedimientosFunciones((yyvsp[(3) - (11)].texto),cuadruploDeEntrada);;}
    break;

  case 47:
#line 260 "sintactico.y"
    {/*printf("funcion \n");*/;}
    break;

  case 53:
#line 268 "sintactico.y"
    {existeVarAsignar(startProList->headTvarPtr,startProList->headTvarPtr->nextPtr,(yyvsp[(1) - (1)].texto),startProList);;}
    break;

  case 54:
#line 268 "sintactico.y"
    {nombreDeVariable=(yyvsp[(1) - (2)].texto);;}
    break;

  case 55:
#line 268 "sintactico.y"
    {ochoExp(8);;}
    break;

  case 57:
#line 270 "sintactico.y"
    {nueveExp(nombreDeVariable);;}
    break;

  case 60:
#line 275 "sintactico.y"
    {eragltc=gltc; gltc=3; ;}
    break;

  case 61:
#line 275 "sintactico.y"
    {unoEstIf();;}
    break;

  case 62:
#line 275 "sintactico.y"
    {gltc=eragltc;}
    break;

  case 65:
#line 278 "sintactico.y"
    {dosEstIf();;}
    break;

  case 66:
#line 278 "sintactico.y"
    {tresEstIf();;}
    break;

  case 69:
#line 285 "sintactico.y"
    {EscrituraWrite((yyvsp[(1) - (1)].texto));;}
    break;

  case 76:
#line 295 "sintactico.y"
    {eragltc=gltc; gltc=3;;}
    break;

  case 77:
#line 295 "sintactico.y"
    {unoEstWhile();;}
    break;

  case 78:
#line 295 "sintactico.y"
    {dosEstWhile();;}
    break;

  case 79:
#line 295 "sintactico.y"
    {tresEstWhile();;}
    break;

  case 80:
#line 295 "sintactico.y"
    {gltc=eragltc;}
    break;

  case 82:
#line 299 "sintactico.y"
    {if (esComparacion==1){
  cuadruplosComparaciones(tipoComp);
};}
    break;

  case 84:
#line 305 "sintactico.y"
    {unollamada((yyvsp[(1) - (1)].texto));;}
    break;

  case 91:
#line 314 "sintactico.y"
    {dosExp(1);;}
    break;

  case 92:
#line 315 "sintactico.y"
    {dosExp(2);;}
    break;

  case 94:
#line 317 "sintactico.y"
    {cuatroExp();;}
    break;

  case 98:
#line 321 "sintactico.y"
    {tresExp(3);;}
    break;

  case 99:
#line 322 "sintactico.y"
    {tresExp(4);;}
    break;

  case 101:
#line 324 "sintactico.y"
    {cincoExp();;}
    break;

  case 105:
#line 328 "sintactico.y"
    {seisExp();;}
    break;

  case 106:
#line 328 "sintactico.y"
    {sieteExp();;}
    break;

  case 107:
#line 329 "sintactico.y"
    {eragltc=gltc; gltc=4;;}
    break;

  case 108:
#line 329 "sintactico.y"
    {gltc=eragltc;;}
    break;

  case 109:
#line 330 "sintactico.y"
    {diezExp((yyvsp[(1) - (1)].texto));

;}
    break;

  case 110:
#line 333 "sintactico.y"
    {unoExpIntCons((yyvsp[(1) - (1)].numero));;}
    break;

  case 111:
#line 334 "sintactico.y"
    {unoExpFloatCons((yyvsp[(1) - (1)].real));;}
    break;

  case 112:
#line 335 "sintactico.y"
    {/*unoExpStrCons($2);*/;}
    break;

  case 113:
#line 339 "sintactico.y"
    {esComparacion=1;tipoComp=11;;}
    break;

  case 114:
#line 340 "sintactico.y"
    {esComparacion=1;tipoComp=12;;}
    break;

  case 115:
#line 341 "sintactico.y"
    {esComparacion=1;tipoComp=13;;}
    break;

  case 116:
#line 342 "sintactico.y"
    {esComparacion=1;tipoComp=14;;}
    break;


/* Line 1267 of yacc.c.  */
#line 2018 "sintactico.tab.c"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;


  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse look-ahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse look-ahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#ifndef yyoverflow
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEOF && yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}


#line 361 "sintactico.y"

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
    insertTabCons (&startTabCons,valor,contStrCons);
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
    insertTabCons (&startTabCons,valor,contBoolCons);
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
      push (&PilaO,resultado);
      pop(&PilaO);
      int operando2=0;

      insertCuadruplos( &startCuadruplos, operacion, operando1, operando2,resultado);
    }
  }
}
void diezExp(char *nombre){
  //revisa que los nombres de las variables y funciones no existan 
    TvarNodoPtr existePtr;
    existePtr = startProList->headTvarPtr;

    TproNodoPtr variablesGlobales;
    variablesGlobales=startProList;
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
int direccionVariable(char *nombre){
//reviza la direccion de las variables; 
    TvarNodoPtr existePtr; 
    existePtr = startProList->headTvarPtr;
    //printf("%s\n",startProList->headTvarPtr->nombreVariable );
    TproNodoPtr variablesGlobales;
    variablesGlobales=startProList;
    int i;
    int direccion;
    int existe;

    while ( existePtr != NULL) {
      i = strcmp (nombre, existePtr->nombreVariable);
       //printf("%s-%s\n",nombre,existePtr->nombreVariable);
      if (i == 0){
        direccion = existePtr->direccion;
        existe=1;
        break;
        //printf ("%d\n",direccion);
      }
      existePtr = existePtr->nextPtr;
    } // end while
    
    if (existe==0){
      while ( variablesGlobales != NULL) {
        i = strcmp ("Global", variablesGlobales->nombreFuncion);
        //printf("%s-%s\n",nombre,existePtr->nombreVariable);
        existePtr=variablesGlobales->headTvarPtr;
        if (i == 0){
         while ( existePtr != NULL) {
          i = strcmp (nombre, existePtr->nombreVariable);
          //printf("%s-%s\n",nombre,existePtr->nombreVariable);
          if (i == 0){
            direccion = existePtr->direccion;
            break;
        //printf ("%d\n",direccion);
          }
          existePtr = existePtr->nextPtr;
          } // end while

        }
        existePtr = existePtr->nextPtr;
      } // end while

    }
    return direccion;

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
void unoEstIf(){
  //21 gotof
  insertCuadruplos(&startCuadruplos, 21,PilaO->data,0,0);
  pop(&PilaO);
  push (&Saltos,contSaltos-1);
}
void dosEstIf(){
  insertCuadruplos(&startCuadruplos,20,0,0,0); //genera goto
  int diferenciaCuadruplos; // diferencia de cuadruplos para ubicacion
  CuadruplosPtr memoriaCuadruplo; //apunador al cuadruplo para insertar #de cuadruplo en f
  memoriaCuadruplo = startCuadruplos;// indica donde esta el apuntador del cuadruplo para retroceder la diferenciad de cuadruplos
  diferenciaCuadruplos = contSaltos-Saltos->data;
  memoriaCuadruplo=startCuadruplos;
  for (int i=0;i<diferenciaCuadruplos-1;i++){
    memoriaCuadruplo=memoriaCuadruplo->nextPtr;
  }
  memoriaCuadruplo->dirResultado = contSaltos;
  pop(&Saltos);// sacar falso de PSaltos
  push (&Saltos,contSaltos-1);
}
void tresEstIf(){
  int diferenciaCuadruplos;
  CuadruplosPtr memoriaCuadruplo;
  memoriaCuadruplo = startCuadruplos;
  diferenciaCuadruplos=contSaltos-Saltos->data;
  memoriaCuadruplo = startCuadruplos;
  for (int i=0;i<diferenciaCuadruplos-1;i++){
    memoriaCuadruplo=memoriaCuadruplo->nextPtr;
  }
  memoriaCuadruplo->dirResultado = contSaltos;
  pop(&Saltos); 
}



void EscrituraWrite(char *nombre){
  int direccion;
  direccion = direccionVariable(nombre);
  insertCuadruplos(&startCuadruplos,30,direccion,0,0);
}
void unoEstWhile(){
  push(&Saltos, contSaltos);
}
void dosEstWhile(){
  insertCuadruplos(&startCuadruplos, 21,PilaO->data,0,0);
  pop(&PilaO);
  push (&Saltos,contSaltos-1);
}
void tresEstWhile(){
  int retorno;
  int falso;
  falso=Saltos->data;
  pop(&Saltos);
  retorno=Saltos->data;
  insertCuadruplos(&startCuadruplos, 20,0,0,retorno);

  int diferenciaCuadruplos;
  CuadruplosPtr memoriaCuadruplo;
  memoriaCuadruplo = startCuadruplos;
  diferenciaCuadruplos=contSaltos-Saltos->data-1;
  memoriaCuadruplo = startCuadruplos;
  for (int i=0;i<diferenciaCuadruplos-1;i++){
    memoriaCuadruplo=memoriaCuadruplo->nextPtr;
  }
  memoriaCuadruplo->dirResultado = contSaltos;
  pop(&Saltos);


}
void tablaDeProcedimientosGlobal(){

  FILE *archivo;/*El manejador de archivo*/
      archivo=fopen("codigointermedio.txt", "w");
      if(archivo==NULL)/*So no lo logramos abrir, salimos*/
         exit(EXIT_FAILURE);
        fprintf(archivo, "%d-%d-%d-%d\n",globalesInt,globalesFloat,globalesStr,globalesBool);
      fclose(archivo);
}
void tablaDeProcedimientosMain(){
    mainInt=cuentaInt;
    cuentaInt=0;
    mainFloat=cuentaFloat;
    cuentaFloat=0;
    mainStr=cuentaStr;
    cuentaStr=0;
    mainBool=cuentaBool;
    cuentaBool =0;
   FILE *archivo;/*El manejador de archivo*/
      archivo=fopen("codigointermedio.txt", "a");
      if(archivo==NULL)/*So no lo logramos abrir, salimos*/
         exit(EXIT_FAILURE);
      fprintf(archivo,"Main-0-%d-%d-%d-%d-%d-%d-%d-%d\n",mainInt,mainFloat,mainStr,mainBool,contEntTmp-10000,contFlotTmp-11000,contStrTmp-12000,contBoolTmp-13000);
      restaEnt=contEntTmp-10000;
      restaFlo=contFlotTmp-11000;
      restaStr=contStrTmp-12000;
      restaBool=contBoolTmp-13000;
      fclose(archivo);
    insertCuadruplos(&startCuadruplos,100,0,0,0);
}
void tablaDeProcedimientosFunciones(char *nombre, int cuadruploDeEntrada){
  mainInt=cuentaInt;
    cuentaInt=0;
    mainFloat=cuentaFloat;
    cuentaFloat=0;
    mainStr=cuentaStr;
    cuentaStr=0;
    mainBool=cuentaBool;
    FILE *archivo;/*El manejador de archivo*/
      archivo=fopen("codigointermedio.txt", "a");
      if(archivo==NULL)/*So no lo logramos abrir, salimos*/
         exit(EXIT_FAILURE);
      fprintf(archivo,"%s-%d-%d-%d-%d-%d-%d-%d-%d-%d\n",nombre,cuadruploDeEntrada,mainInt,mainFloat,mainStr,mainBool,contEntTmp-10000-restaEnt,contFlotTmp-11000-restaFlo,contStrTmp-12000-restaStr,contBoolTmp-13000-restaBool);
      restaEnt=contEntTmp-10000;
      restaFlo=contFlotTmp-11000;
      restaStr=contStrTmp-12000;
      restaBool=contBoolTmp-13000;
      fclose(archivo);
      insertCuadruplos(&startCuadruplos,51,0,0,0);

}
void unollamada(char *nombre){
  TproNodoPtr newProPtr;
  TproNodoPtr previousProPtr;
  previousProPtr = NULL;
  existPro ( startProList, previousProPtr, nombre);
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
  FILE *archivo;/*El manejador de archivo*/
      archivo=fopen("codigointermedio.txt", "a");
      if(archivo==NULL)/*So no lo logramos abrir, salimos*/
         exit(EXIT_FAILURE);
      fprintf(archivo, "-\n");
  fclose(archivo);
  escribeCuadruplos( startCuadruplos );
  escribeTabCons(startTabCons);
  
  //FILE *archivo;/*El manejador de archivo*/
    // archivo=fopen("codigointermedio.txt", "a");
     //if(archivo==NULL)/*So no lo logramos abrir, salimos*/
       //  exit(EXIT_FAILURE);
      //fprintf(archivo, "-\n");
      //fprintf(archivo, "%d-%d-%d-%d\n",globalesInt,globalesFloat,globalesStr,globalesBool);
      //fprintf(archivo,"Main-0-%d-%d-%d-%d-%d-%d-%d-%d",mainInt,mainFloat,mainStr,mainBool,contEntTmp-10000,contFlotTmp-11000,contStrTmp-12000,contBoolTmp-13000);
      //fclose(archivo);
/*
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
  */
  printf("0" );
  return 0;
  
}

