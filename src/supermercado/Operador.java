/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.08.22
 * @desc Lógica de negocio de Supermercados Santa Fé
 */
public final class Operador {

    private final List<Empleado> empleados = new ArrayList<Empleado>();
    private final List<String> departamentos = new ArrayList<String>();
    private final List<String> puestos = new ArrayList<String>();
    private static final Object[] opciones = {"Seleccione", "Agregar Empleado Nuevo", "Buscar Empleado", "Salario de Departamento", "Cantidad de Empleados por Puesto", "Salir"};

    public Operador() {
        departamentos.add("Seleccione");
        puestos.add("Seleccione");
    }

    public void _start() {
        _seleccion();
    }

    public void _registroEmpleado() {
        String cedula, nombre, departamento, posicion, salario;

        cedula = (String) JOptionPane.showInputDialog(null, "Digite la cedula del empleado (solo números)", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "");
        nombre = (String) JOptionPane.showInputDialog(null, "Digite el nombre del empleado", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "");
        departamento = (String) JOptionPane.showInputDialog(null, "Digite el departamento del empleado", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "");
        posicion = (String) JOptionPane.showInputDialog(null, "Digite la posicion del empleado", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "");
        salario = (String) JOptionPane.showInputDialog(null, "Digite el salario del empleado (solo números)", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "");

        if (cedula == null || nombre == null || departamento == null || posicion == null || salario == null) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo realizar el registro del empleado.\nVolvera al inicio.");

            _start();
        } else {

            _agregarEmpleado(cedula, nombre, departamento, posicion, salario);

            JOptionPane.showMessageDialog(
                    null,
                    "El empleado de cedula " + cedula + " ha sido registrado con exito.\nVolvera al inicio.");

            _start();

        }
    }

    private void _agregarEmpleado(String _cedula, String _nombre, String _departamento, String _posicion, String _salario) {
        if (!departamentos.contains(_departamento)) {
            departamentos.add(_departamento);
        }

        if (!puestos.contains(_posicion)) {
            puestos.add(_posicion);
        }

        Empleado e = new Empleado(_cedula, _nombre, _departamento, _posicion, Double.parseDouble(_salario));

        if (!_existeEmpleado(empleados, _cedula)) {
            empleados.add(e);
        }
    }

    private Boolean _existeEmpleado(final List<Empleado> l, final String cedula) {
        return l.stream().filter(o -> o.getCedula().equals(cedula)).findFirst().isPresent();
    }

    public void _busquedaEmpleado() {
        String cedula = (String) JOptionPane.showInputDialog(null, "Digite la cedula del empleado", opciones[2].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "");

        for (Empleado empleado : empleados) {
            if (empleado.getCedula().equals(cedula)) {
                String msj = "";

                msj += "Cedula: " + empleado.getCedula();
                msj += "\nNombre: " + empleado.getNombre();
                msj += "\nDepartamento: " + empleado.getDepartamento();
                msj += "\nPosicion: " + empleado.getPosicion();
                msj += "\nSalario: " + empleado.getSalario();

                JOptionPane.showMessageDialog(null, msj, opciones[2].toString(), JOptionPane.INFORMATION_MESSAGE, null);
            }
        }

        _start();
    }

    public void _calcularSalarioDepartamento() {

        Object depSeleccionado = JOptionPane.showInputDialog(
                null,
                opciones[3].toString(),
                "Supermercados Santa Fé",
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono defecto
                departamentos.toArray(),
                departamentos.get(0));

        if (depSeleccionado == null) {
            _start();
        } else if (depSeleccionado.toString().length() > 0) {

            if (!depSeleccionado.toString().equals(departamentos.get(0))) {
                double suma = 0;

                for (Empleado empleado : empleados) {
                    if (empleado.getDepartamento().equals(depSeleccionado)) {
                        suma += empleado.getSalario();
                    }
                }

                String msj = "";

                msj += "Departamento: " + depSeleccionado;
                msj += "\nSalario Total: $" + suma;

                JOptionPane.showMessageDialog(null, msj, opciones[3].toString(), JOptionPane.INFORMATION_MESSAGE, null);

                _start();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Registre un empleado para poder calcular el salario del departamento.\nVolvera al inicio.");

                _start();
            }
        }
    }

    public void _cantidadEmpleadosPuesto() {

        Object posSeleccionado = JOptionPane.showInputDialog(
                null,
                opciones[4].toString(),
                "Supermercados Santa Fé",
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono defecto
                puestos.toArray(),
                puestos.get(0));

        if (posSeleccionado == null) {
            _start();
        } else if (posSeleccionado.toString().length() > 0) {

            if (!posSeleccionado.toString().equals(puestos.get(0))) {
                int suma = 0;

                for (Empleado empleado : empleados) {
                    if (empleado.getPosicion().equals(posSeleccionado.toString())) {
                        suma += 1;
                    }
                }

                String msj = "";

                msj += "Posición: " + posSeleccionado.toString();
                msj += "\nCantidad Total: " + suma;

                JOptionPane.showMessageDialog(null, msj, opciones[3].toString(), JOptionPane.INFORMATION_MESSAGE, null);

                _start();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Registre un empleado para poder calcular la cantidad de trabajadores por posición.\nVolvera al inicio.");

                _start();
            }
        }
    }

    public void _seleccion() {
        JOptionPane.showMessageDialog(
                null,
                "Bienvenidos a Supermercados Santa Fé\nSeleccione una opción correcta, por favor.");

        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Menú de Opciones",
                "Supermercados Santa Fé",
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono defecto
                opciones,
                opciones[0]);

        if (seleccion == null) {
            _start();
        } else if (seleccion.toString().length() > 0) {

            if (seleccion.equals(opciones[0])) {
                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione una opción correcta, por favor.");

            } else if (seleccion.equals(opciones[1])) {
                _registroEmpleado();
            } else if (seleccion.equals(opciones[2])) {
                _busquedaEmpleado();
            } else if (seleccion.equals(opciones[3])) {
                _calcularSalarioDepartamento();
            } else if (seleccion.equals(opciones[4])) {
                _cantidadEmpleadosPuesto();
            } else if (seleccion.equals(opciones[5])) {
                _exit();
            }
        }
    }

    public void _exit() {

        switch (JOptionPane.showConfirmDialog(null, "¿Señor(a) Usuario(a), ¿Desea salir del administrador?", "Supermercados Santa Fé",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
            case 0 -> {
                JOptionPane.showMessageDialog(null, "Gracias por usar el administrador de Supermercados Santa Fé.", "Supermercados Santa Fé", JOptionPane.INFORMATION_MESSAGE, null);
            }
            case 1 -> {
                JOptionPane.showMessageDialog(null, "Volvera al inicio.", "Supermercados Santa Fé", JOptionPane.INFORMATION_MESSAGE, null);

                _start();
            }
        }

    }

}
