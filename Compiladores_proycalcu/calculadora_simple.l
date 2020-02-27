%option yylineno
%{
#include "calculadora_simple.tab.h" //asciitofloat(atof)
%}
DIGITO [0-9]
SENO [sen]+
COSENO [cos]+
TANGENTE [tan]+
RAIZ [raiz]+
%%
{DIGITO}+("."{DIGITO}+)? {yylval.real=atof(yytext); return(TKN_NUM);}
";"	{return(TKN_PTOCOMA);}
"("	{return(TKN_PA);}
")"	{return(TKN_PC);}
"+"     {return(TKN_MAS);}
"-"     {return(TKN_MENOS);}
"*"     {return(TKN_MULT);}
"/"     {return(TKN_DIV);}
"^"     {return(TKN_POW);}
{RAIZ}	{return(TKN_RAIZ);}
{COSENO}	{return TKN_COS;}
{SENO}		{return TKN_SEN;}
{TANGENTE}	{return TKN_TAN;}
\n  	{}
%%

