package modelo;

import java.util.Date;
import java.text.SimpleDateFormat;
public class FechaHoy {
    private Date fecha;
    private SimpleDateFormat formato;
    private int[] meses;

    public FechaHoy() {

        meses = new int[12];
        llenarMeses() ;
        this.fecha = new Date();
        this.formato = new SimpleDateFormat("dd-MM-yyyy") ;

    }

    public void obterFecha(char[] fechaComparar)
    {
        //se verifica que la fecha ingresada cumpla con la estructura que se utilizara
        if(!verificarEstructura(fechaComparar)) return;

        //la funcion crea una variable con la fecha de hoy con el formato de la linea 25
        char[] fechaHoy = formato.format(fecha).toCharArray();

        //datos de la fecha de hoy
        int yearHoy = obtenerYear(fechaHoy);
        int mesHoy = obtenerMes(fechaHoy);
        int diaHoy = obtenerDia(fechaHoy);

        //datos de la fecha a comparar/ingresada
        int yearComparar = obtenerYear(fechaComparar);
        int mesComparar = obtenerMes(fechaComparar);
        int diaComparar = obtenerDia(fechaComparar);

        if(verificarFechas(yearHoy, mesHoy, diaHoy, yearComparar, mesComparar, diaComparar))
        {
            int diasR = diasRestantes(diaHoy, mesHoy, diaComparar, mesComparar) ;
            int MesesR = mesesRestantes(mesHoy, mesComparar);
            int yearR = yearRestante(yearComparar, yearHoy, mesHoy, mesComparar);
            //jText.append("quedan: " + diasR + " dias"+'\n');
            //jText.append("quedan: " + MesesR + " meses"+'\n');
            //jText.append("quedan: " + yearR + " años"+'\n');
        }
        else
        {
            //jText.append("La obra esta fuera de fecha"+'\n');
        }
    }

    private boolean verificarEstructura(char[] fechaComparar) //se debe verificar que se cumpla la estructura base dd-MM-yyyy
    {
        if((fechaComparar[2] != '-') || (fechaComparar[5] != '-'))
        {
            return false;
        }
        try
        {
            Integer.parseInt(String.valueOf(fechaComparar[0])) ;
            Integer.parseInt(String.valueOf(fechaComparar[1])) ;
            Integer.parseInt(String.valueOf(fechaComparar[3])) ;
            Integer.parseInt(String.valueOf(fechaComparar[4])) ;
            Integer.parseInt(String.valueOf(fechaComparar[6])) ;
            Integer.parseInt(String.valueOf(fechaComparar[7])) ;
            Integer.parseInt(String.valueOf(fechaComparar[8])) ;
            Integer.parseInt(String.valueOf(fechaComparar[9])) ;
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    //Metodos privados
    private boolean verificarFechas(int yearHoy, int mesHoy, int diaHoy, int yearComparar, int mesComparar, int diaComparar)//Verificar que las fechas sean comparables
    {
        //Si la fecha ya paso se retornara false
        if(yearHoy > yearComparar)
        {
            //System.out.println("La obra esta fuera de fecha");
            return false ;
        }
        if((yearHoy == yearComparar) && (mesHoy > mesComparar))
        {
            //System.out.println("La obra esta fuera de fecha");
            return false;
        }
        if((yearHoy == yearComparar) && (mesHoy == mesComparar) && (diaHoy > diaComparar))
        {
            //System.out.println("La obra esta fuera de fecha");
            return false;
        }
        //En caso que la fecha aun no haya ocurido retornaremos true
        return true;
    }

    private int obtenerYear(char[] fechaComparar)
    {
        //Se obtiene el año de la fecha a comparar
        int i;
        int j;
        int year = 0;
        for(i = 9, j = 1; i > 5 ; i--,j = j*10)
        {
            if(fechaComparar[i] == 0) continue;
            year = (Integer.parseInt(String.valueOf(fechaComparar[i])) * j) + year;
        }
        return year;
    }
    private int obtenerMes(char[] fechaComparar)
    {
        //Se obtiene el mes de la fecha a comparar
        int i;
        int j;
        int mes = 0;
        for(i = 4, j = 1; i > 2 ; i--,j = j*10)
        {
            if(fechaComparar[i] == 0) continue;
            mes = (Integer.parseInt(String.valueOf(fechaComparar[i])) * j) + mes;
        }
        return mes;
    }
    private int obtenerDia(char[] fechaComparar)
    {
        //Se obtiene el dia de la fecha a comparar
        int i;
        int j;
        int dia = 0;
        for(i = 1, j = 1; i > -1 ; i--,j = j*10)
        {
            if(fechaComparar[i] == 0) continue;
            dia = (Integer.parseInt(String.valueOf(fechaComparar[i])) * j) + dia;
        }
        return dia;
    }

    private int diasRestantes(int diaHoy, int mesHoy, int diaComparar, int mesComparar)
    {
        //existe un contador de dias
        int contadorDias = 0;
        //año bisiesto
        if((mesComparar == 2) && (diaComparar == 29))
        {
            contadorDias++;
        }
        int i ;
        //se cuentan los dias que faltan para terminar el mes
        for(i = diaHoy ; i < meses.length ; i++)
        {
            contadorDias++;
        }
        //se cuentan los dias que tiene el mes objetivo ej: 12 de diciembre -> este mes tiene 12 dias extras
        for(i = diaComparar - 1 ; i > 0 ; i--)
        {
            contadorDias++;
        }
        //En caso de tener mas de un mes en relacion a los dias estos deben ser restados en 30
        if(contadorDias > 29) contadorDias = contadorDias - 30;
        return contadorDias;
    }

    private int mesesRestantes(int mesHoy, int mesComparar) {
        //se restan los meses
        int mesesRestantes = mesComparar - mesHoy;
        //en base a los dias esta condicion se debe complir
        if(mesesRestantes > 0)
        {
            //ya que este mes es parte del mismo año y la relacion de el contador de dias nos daria un mes extra
            mesesRestantes--;
        }
        if(mesesRestantes < 0)
        {
            //Aqui estamos hablando de un mes de el proximo año por lo que debemos aumentar los meses en 12
            //y volver a restar
            mesComparar = mesComparar + 12 ;
            mesesRestantes = mesComparar - mesHoy;
        }

        return mesesRestantes;
    }
    private int yearRestante(int yearComparar, int yearHoy, int mesHoy, int mesComparar) {
        //este es el caso de que el mes a comparar sea del proximo año
        if(mesComparar - mesHoy < 0)
        {
            //por lo que se debe restar un año y añadir 12 meses
            return yearComparar - yearHoy - 1 ;
        }
        return yearComparar - yearHoy;
    }

    private void llenarMeses() {
        //Los meses del año
        this.meses[0] = 31 ;
        this.meses[1] = 28 ;
        this.meses[2] = 31 ;
        this.meses[3] = 30 ;
        this.meses[4] = 31 ;
        this.meses[5] = 30 ;
        this.meses[6] = 31 ;
        this.meses[7] = 31 ;
        this.meses[8] = 30 ;
        this.meses[9] = 31 ;
        this.meses[10] = 30 ;
        this.meses[11] = 31 ;

    }
}
