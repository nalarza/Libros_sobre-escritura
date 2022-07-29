package Api.Libros;

public class LibroVip extends Libro{

    @Override
    public double Detalles(double costoAlquiler ){
        double porcentajeDeAlquiler = costoAlquiler*0.09;
        double costoFinal= costoAlquiler-porcentajeDeAlquiler;
        return costoFinal;
    }
    @Override
    public double Porcentaje(double costoAlquiler){
        double porcentaje = costoAlquiler*0.09;
        return porcentaje;
    }
    @Override
    public  String Detalles(String nombreCompleto,String direccion){
        return "Quien Alquila: "+ nombreCompleto+ ", Direccion: " + direccion ;
    }

}
