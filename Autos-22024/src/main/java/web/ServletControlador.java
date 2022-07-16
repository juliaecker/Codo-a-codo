package web;

import data.LibreriaDAO;
import entity.Autos;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    editarAuto(req,res);
                    break;
                case "eliminar":
                    eliminarAuto(req,res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        } else {
            accionDefault(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    guardarAuto(req, res);
                    break;
                case "modificar":
                    modificarAuto(req,res);
                    break;
                default:
                    accionDefault(req, res);
                    break;

            }
        }
    }

    private void accionDefault(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Autos> autos = new LibreriaDAO().findAll();
        HttpSession sesion = req.getSession();
        sesion.setAttribute("pepe", autos);
        sesion.setAttribute("cantidadAutos", calcularCant(autos));
        sesion.setAttribute("precioTotal", calcularPrecio(autos));
        //req.getRequestDispatcher("libros.jsp").forward(req, res);
        res.sendRedirect("autos.jsp");
    }

    private void guardarAuto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        int año = Integer.parseInt(req.getParameter("año"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        Autos auto = new Autos(marca, modelo, año, precio, stock);
        int regMod = new LibreriaDAO().create(auto);
        System.out.println("Insertados: " + regMod);
        accionDefault(req, res);
    }
    
    private void editarAuto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int idAuto = Integer.parseInt(req.getParameter("idAuto"));
        Autos aut = new LibreriaDAO().findCarById(idAuto);
        req.setAttribute("auto", aut);
        req.getRequestDispatcher("/WEB-INF/paginas/operaciones/editarAuto.jsp").forward(req, res);
    }
    
    private void modificarAuto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        int año = Integer.parseInt(req.getParameter("año"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        int idAuto = Integer.parseInt(req.getParameter("idAuto"));

        Autos aut = new Autos(idAuto, marca, modelo, año, precio, stock);

        int regMod = new LibreriaDAO().update(aut);

        System.out.println("SE ACTUALIZARON: " + regMod + " REGISTROS");

        accionDefault(req, res);
    
    }
        private void eliminarAuto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int idAuto = Integer.parseInt(req.getParameter("idAuto"));
        
        int regDel = new LibreriaDAO().delete(idAuto);
        
        System.out.println("REGISTROS ELIMINADOS: "+ regDel);
        
        accionDefault(req, res);
    }
    

    private int calcularCant(List<Autos> lista) {
        int cantidad = 0;
        for (int i = 0; i < lista.size(); i++) {
            cantidad += lista.get(i).getStock();
        }
        return cantidad;
    }

    private double calcularPrecio(List<Autos> lista) {
        double precio = 0;
        for (int i = 0; i < lista.size(); i++) {
            precio += (lista.get(i).getStock() * lista.get(i).getPrecio());
        }
        return precio;
    }

}