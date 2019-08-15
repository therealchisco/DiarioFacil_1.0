package diarioFacil;

import java.util.Calendar;

public interface IHistorial {
    public void verHistorial();
    public void verHistorialUsuario(int cedulaUsuario);
    public void verHistorialFecha(Calendar fecha);
    public void verHistorialFecha(Calendar fechaInicio, Calendar fechaFin);
}
