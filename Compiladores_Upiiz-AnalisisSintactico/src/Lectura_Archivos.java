/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.ArrayList;
/**
 * Alumnos:
 * Alejandra Montserrat Esparza Rios  2018670112
 * Mariel Lopez Beltran     2018670492
 * Fecha de entrega:
 * 19/05/20
 * Materia:
 * Compiladores
 * Evidencia:
 * Programa: Analizador Sintactico(Tabla de simbolos)
 * Nombre del maestro:
 * Karina Rodriguez Mejía
 * Programa academico:
 * Ingenieria en sistemas computacionales
 * Unidad de aprendizaje:
 * Unidad 3
 * 
 */
public class Lectura_Archivos {
    int caracterL;
    char caracter;
    FileReader re;
    BufferedReader v;
    String direccion="src/Ejercicio1.txt";
    String cadena ="";
    int b;
    int c;
    boolean bandera=true;
    ArrayList<String> cadSemantica=new ArrayList<>();
  
    //Declaracion de metodos
     public Lectura_Archivos() throws FileNotFoundException{     
         re = new FileReader(direccion);
     } 
    public char LecturaCaracteres() throws FileNotFoundException,IOException{        
          caracterL = re.read();  
          caracter =(char)caracterL;   
          return caracter;
    }
    //Referente a espacio en blanco, retorno de carro, salto de linea
   public int probihicionesSalto(int d) throws IOException{
        char r;
        if(d==10 || d==13 || d==32){
        r=LecturaCaracteres();
        d=(int)r;               
        } 
         return d;
    }
   //Referente a si no es un simbolo que se encuentre dentro de la tabla de simbolos
   public void MensajeError(char c){
       System.out.print("Simbolo no valido "+c);
   }
   public String Palabrasreservadas(String cad) throws IOException{
       //Metodo para el analizador sintactico
   //Palabras reservadas
       ArrayList reservadas=new ArrayList(30);
       reservadas.add("Accio"); //Int
       reservadas.add("Snitch");//Var
       reservadas.add("Dementor");//Float
       reservadas.add("Winky");//Char
       reservadas.add("Dobby");//String
       reservadas.add("Kreacher");//Boolean
       reservadas.add("Bombarda"); //Public
       reservadas.add("Lupin");//Proceso Llamar
       reservadas.add("Fred");//If
       reservadas.add("George");//Else
       reservadas.add("Wingardium");//Condicion
       reservadas.add("Albus");//Entonces
       reservadas.add("Leviosa");//Proposicion
       reservadas.add("Luna"); //Cambiar
       reservadas.add("Niffler"); //Caso
       reservadas.add("Hedwig"); //Expre
       reservadas.add("Occamy");//Hacer
       reservadas.add("Alohomora");//For
       reservadas.add("Voldemort");//Bloque
       reservadas.add("Hermione");//Y
       reservadas.add("Ron");//O
       reservadas.add("Snape");//Void
       
     //Verificacion de las palabras reservadas
       boolean existe=reservadas.contains(cad);
        if(existe){
            cadena=cad;
            //e=LecturaCaracteres();
            cadSemantica.add(cad);
        }
        else{
          cadena="Newt";//IDENT
          //  e=LecturaCaracteres();              
   }
   return cadena;
   }
   //Validacion de la tabla de simbolos
   public int  prohibionesSimbolo(int e) throws IOException{
       char r;
       ArrayList simbolos = new ArrayList(15);
       simbolos.add(39);// '
       simbolos.add(40); //(
       simbolos.add(41); //)
       simbolos.add(42);// *
       simbolos.add(43);// +
       simbolos.add(44);// ,
       simbolos.add(45); // -
       simbolos.add(46); // .
       simbolos.add(47); // /
       simbolos.add(91); //[
       simbolos.add(92); // \
       simbolos.add(93); // ]
       simbolos.add(123); // {
       simbolos.add(125); // }
       simbolos.add(59); // ; 
       simbolos.add(94); //^
       boolean existe=simbolos.contains(e);
       if(existe){
        cadena+=(char)e;       
        r=LecturaCaracteres();        
        e=(int)r;
       }else{
           MensajeError((char)e);
        r=LecturaCaracteres();        
        e=(int)r;
       }  
      
       return e;
   }
   
    
   public String VCadena() throws IOException{
        char v; 
        cadena="";      
        if(bandera==true){
        v=LecturaCaracteres();        
        c=(int)v;  
        bandera=false;
        }//if de bandera
        //Prohibiciones
        c=probihicionesSalto(c);
        //Primer parte de la expresion ([a-z]|[A-Z]|_)
        if((c==95) ||(c>=97 && c<=122) || (c>=65 && c<=90) ){        
        cadena+=(char)c;           
        v=LecturaCaracteres();        
        c=(int)v;
        //Segunda parte de la expresion regular ([a-z]|[A-Z]|_|[0-9])*
        while((c==95) ||(c>=97 && c<=122) || (c>=65 && c<=90) || ((c>=48) && (c<=57))){
        {       
         cadena+=(char)c;
        v=LecturaCaracteres();
        c=(int)v;
        }
         
            
    } 
        //Verificacion de palabras reservadas
        cadena=Palabrasreservadas(cadena);
        ///ProhibicionesSalto
        c=probihicionesSalto(c);
        
    }else{ ///Numeros [0-9]+(.[0-9]+)?
            if((c>=48 && c<=57)){//Verificacion del primer numero
                cadena+=(char)c;
               
                v=LecturaCaracteres();
                c=(int)v;             
                while((c>=48 && c<=57)){ //Verificacion si existen mas numeros
                   cadena+=(char)c;     
                   
                   v=LecturaCaracteres();
                   c=(int)v;
                }
                if((c==46)){ //Verificacion de decimales si es que existen
                    v=LecturaCaracteres();
                    c=(int)v;                
                  if((c>=48 && c<=57)) { //Concatenacion de punto
                     cadena+="."+(char)c;
                     v=LecturaCaracteres();
                     c=(int)v;
                     while((c>=48 && c<=57)){
                   cadena+=(char)c;
                   
                   v=LecturaCaracteres();
                   c=(int)v;
                   }
                }
                }   
                cadena="Harry"; //Si es un numero
            }else{                  
                if(c>59 && c<63 || c==33){ //Operadores <=,>=,!=,==,<,>
                    cadena+=(char)c;                   
                   v=LecturaCaracteres();
                   c=(int)v;
                   if(c==61){
                   cadena+=(char)c;  
                   v=LecturaCaracteres();
                   c=(int)v;   
                   }
                }else{
                    if(c==124 ){ //Logicos por ejemplo && ||
                    cadena+=(char)c;                   
                   v=LecturaCaracteres();
                   c=(int)v;
                   if(c==124){
                    cadena+=(char)c;            
                   v=LecturaCaracteres();
                   c=(int)v;
                   }
                    }
                    else{
                      if(c==38 ){
                    cadena+=(char)c;                   
                   v=LecturaCaracteres();
                   c=(int)v;
                   if(c==38){
                    cadena+=(char)c;            
                   v=LecturaCaracteres();
                   c=(int)v;   
                    }      
                }           
                //Verificacion en la tabla de simbolos           
                c=prohibionesSimbolo(c);       
             }
         
            }//if de los operadores logicos 
                ///Prohibiciones
         c=probihicionesSalto(c);
        }
        }//if de los numeros  
         ///Prohibiciones
        if(c==10 || c==13 || c==32){
            v=LecturaCaracteres();
        c=(int)v; 
        }  
        
        ///Prohibiciones
        c=probihicionesSalto(c);
       //segundo if validacion de letras                   
        return cadena; //Retorna la cadena que se ha formado
    }  
    public static void main(String[] args)  throws IOException{
        Lectura_Archivos v = new Lectura_Archivos();
        for(int i=0;i<30;i++){
             System.out.println(v.VCadena()); 
        }
           
      System.out.print("Numero de elementos guardados en el analizador semantico: "+v.cadSemantica.size()+"\n");

    
                            
    }
   
    
    
}
