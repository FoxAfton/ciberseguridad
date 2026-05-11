package Equipo_7.Negocio;

/**
 * Clase encargada de validar los datos de un objeto {@link Trabajador}.
 * <p>
 * Cada campo del trabajador cuenta con dos métodos:
 * <ul>
 *   <li><b>validar*()</b> – retorna {@code boolean}, útil para validaciones rápidas.</li>
 *   <li><b>mensaje*()</b> – retorna {@code String} con el error, o {@code null} si es válido.</li>
 * </ul>
 * El método principal {@link #validarTrabajador(Trabajador)} recibe el objeto completo
 * y retorna el primer error encontrado, o {@code null} si todos los campos son válidos.
 * </p>
 *
 * @author SrMrPandora
 * @see Trabajador
 */
public class ValidatorClass {

  
    /**
     * Valida todos los campos del objeto {@link Trabajador} en orden.
     * <p>
     * El orden de validación es:
     * RFC → NSS → Nombre → Email → Fecha de Alta → Sueldo → Alta IMSS
     * </p>
     *
     * @param t Objeto {@link Trabajador} cuyos datos se desean validar.
     * @return {@code String} con el mensaje del primer error encontrado,
     *         o {@code null} si todos los campos son válidos.
     *
     * @example
     * <pre>
     *     Trabajador t = new Trabajador(...);
     *     String error = ValidatorClass.validarTrabajador(t);
     *     if (error != null) {
     *         JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.WARNING_MESSAGE);
     *     }
     * </pre>
     */
    public static String validarTrabajador(Trabajador t) {
        String error;
        if ((error = mensajeRFC(t.getRFC()))            != null) return error;
        if ((error = mensajeNSS(t.getAltaImss()))       != null) return error;
        if ((error = mensajeNombre(t.getNombre()))      != null) return error;
        if ((error = mensajeEmail(t.getEmail()))        != null) return error;
        if ((error = mensajeFecha(t.getFechadeAlta()))  != null) return error;
        if ((error = mensajeSueldo(t.getSueldo()))      != null) return error;
        if ((error = mensajeAltaIMSS(t.getAltaImss())) != null) return error;
        return null;
    }

  
    /**
     * Verifica que el RFC tenga el formato oficial mexicano.
     * <p>
     * Formato esperado: {@code 3-4 letras + 6 dígitos (AAMMDD) + 2-3 alfanuméricos}
     * </p>
     * <ul>
     *   <li>Longitud: 12 o 13 caracteres.</li>
     *   <li>Caracteres permitidos: A-Z, 0-9, Ñ y &amp;.</li>
     *   <li>No permite minúsculas ni caracteres especiales.</li>
     * </ul>
     *
     * @param rfc Cadena con el RFC a validar.
     * @return {@code true} si el RFC cumple el formato; {@code false} en caso contrario.
     *
     * @example Valor válido: {@code "ABCD850101XY3"}
     */
    public static boolean validarRFC(String rfc) {
        if (rfc == null || rfc.isBlank()) return false;
        return rfc.matches("^[A-ZÑ&]{3,4}[0-9]{6}[A-Z0-9]{2,3}$");
    }

    /**
     * Genera un mensaje de error descriptivo si el RFC es inválido.
     *
     * @param rfc Cadena con el RFC a evaluar.
     * @return Mensaje de error como {@code String}, o {@code null} si el RFC es válido.
     */
    public static String mensajeRFC(String rfc) {
        if (rfc == null || rfc.isBlank())
            return "El RFC no puede estar vacío.";
        if (rfc.length() < 12)
            return "El RFC es demasiado corto (mínimo 12 caracteres).";
        if (!validarRFC(rfc))
            return "El RFC tiene un formato inválido. Ejemplo válido: ABCD850101XY3";
        return null;
    }

  
    /**
     * Verifica que el Número de Seguro Social (NSS) sea válido.
     * <ul>
     *   <li>Debe contener exactamente 11 dígitos numéricos.</li>
     *   <li>No se permiten letras, espacios ni caracteres especiales.</li>
     * </ul>
     *
     * @param nss Cadena con el NSS a validar.
     * @return {@code true} si el NSS tiene exactamente 11 dígitos; {@code false} en caso contrario.
     *
     * @example Valor válido: {@code "12345678901"}
     */
    public static boolean validarNSS(String nss) {
        if (nss == null || nss.isBlank()) return false;
        return nss.matches("^[0-9]{11}$");
    }

    /**
     * Genera un mensaje de error descriptivo si el NSS es inválido.
     *
     * @param nss Cadena con el NSS a evaluar.
     * @return Mensaje de error como {@code String}, o {@code null} si el NSS es válido.
     */
    public static String mensajeNSS(String nss) {
        if (nss == null || nss.isBlank())
            return "El número de seguro social no puede estar vacío.";
        if (!nss.matches("^[0-9]+$"))
            return "El número de seguro social solo debe contener dígitos, sin letras ni símbolos.";
        if (nss.length() != 11)
            return "El número de seguro social debe tener exactamente 11 dígitos.";
        return null;
    }

   
    /**
     * Verifica que el nombre completo sea válido.
     * <ul>
     *   <li>Solo permite letras (incluye acentos y ñ), espacios y guiones.</li>
     *   <li>No se permiten números ni caracteres especiales.</li>
     *   <li>Mínimo 5 caracteres.</li>
     *   <li>Debe contener al menos dos palabras (nombre + apellido).</li>
     * </ul>
     *
     * @param nombre Cadena con el nombre completo a validar.
     * @return {@code true} si el nombre es válido; {@code false} en caso contrario.
     *
     * @example Valor válido: {@code "Juan Pérez López"}
     */
    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        String recortado = nombre.trim();
        if (recortado.length() < 5) return false;
        if (!recortado.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ][a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' -]*$")) return false;
        return recortado.split("\\s+").length >= 2;
    }

    /**
     * Genera un mensaje de error descriptivo si el nombre es inválido.
     *
     * @param nombre Cadena con el nombre completo a evaluar.
     * @return Mensaje de error como {@code String}, o {@code null} si el nombre es válido.
     */
    public static String mensajeNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            return "El nombre no puede estar vacío.";
        String recortado = nombre.trim();
        if (recortado.length() < 5)
            return "El nombre es demasiado corto (mínimo 5 caracteres).";
        if (recortado.matches(".*[0-9].*"))
            return "El nombre no puede contener números.";
        if (!recortado.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ][a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' -]*$"))
            return "El nombre contiene caracteres especiales no permitidos.";
        if (recortado.split("\\s+").length < 2)
            return "Ingrese al menos nombre y un apellido.";
        return null;
    }

   
    /**
     * Verifica que el correo electrónico tenga un formato estándar válido.
     * <ul>
     *   <li>Solo letras minúsculas, dígitos y los símbolos: {@code . _ -}</li>
     *   <li>Debe contener exactamente un {@code @}.</li>
     *   <li>No se permiten espacios ni mayúsculas.</li>
     *   <li>La extensión del dominio debe tener entre 2 y 6 letras.</li>
     * </ul>
     *
     * @param email Cadena con el email a validar.
     * @return {@code true} si el email es válido; {@code false} en caso contrario.
     *
     * @example Valor válido: {@code "usuario@correo.com"}
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.isBlank()) return false;
        return email.matches("^[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
    }

    /**
     * Genera un mensaje de error descriptivo si el email es inválido.
     *
     * @param email Cadena con el email a evaluar.
     * @return Mensaje de error como {@code String}, o {@code null} si el email es válido.
     */
    public static String mensajeEmail(String email) {
        if (email == null || email.isBlank())
            return "El email no puede estar vacío.";
        if (!email.contains("@"))
            return "El email debe contener '@'.";
        if (!validarEmail(email))
            return "Formato de email inválido. Ejemplo: usuario@correo.com";
        return null;
    }

    
    /**
     * Verifica que la fecha de alta tenga el formato {@code DD/MM/YYYY} y sea coherente.
     * <ul>
     *   <li>Formato obligatorio: {@code DD/MM/YYYY}.</li>
     *   <li>El día debe corresponder al mes (ej. no permite 31/04/2024).</li>
     *   <li>El año debe estar entre 1900 y 2100.</li>
     * </ul>
     *
     * @param fecha Cadena con la fecha a validar.
     * @return {@code true} si la fecha es válida; {@code false} en caso contrario.
     *
     * @example Valor válido: {@code "15/03/2024"}
     */
    public static boolean validarFecha(String fecha) {
        if (fecha == null || fecha.isBlank()) return false;
        if (!fecha.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$")) return false;

        String[] partes = fecha.split("/");
        int dia  = Integer.parseInt(partes[0]);
        int mes  = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        if (anio < 1900 || anio > 2100) return false;

        int[] diasPorMes = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return dia <= diasPorMes[mes];
    }

    /**
     * Genera un mensaje de error descriptivo si la fecha es inválida.
     *
     * @param fecha Cadena con la fecha a evaluar.
     * @return Mensaje de error como {@code String}, o {@code null} si la fecha es válida.
     */
    public static String mensajeFecha(String fecha) {
        if (fecha == null || fecha.isBlank())
            return "La fecha de alta no puede estar vacía.";
        if (fecha.length() < 10)
            return "La fecha es demasiado corta. Use el formato DD/MM/YYYY.";
        if (!fecha.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$"))
            return "Formato de fecha inválido. Use DD/MM/YYYY. Ejemplo: 15/03/2024";
        if (!validarFecha(fecha))
            return "La fecha contiene un día inválido para el mes indicado.";
        return null;
    }

   
    /**
     * Verifica que el sueldo base sea un valor numérico positivo válido.
     * <ul>
     *   <li>Solo se permiten dígitos y un punto decimal opcional.</li>
     *   <li>Máximo 2 cifras decimales.</li>
     *   <li>El valor debe ser mayor a {@code 0.00}.</li>
     * </ul>
     *
     * @param sueldo Cadena con el sueldo a validar.
     * @return {@code true} si el sueldo es válido; {@code false} en caso contrario.
     *
     * @example Valor válido: {@code "8500.50"}
     */
    public static boolean validarSueldo(String sueldo) {
        if (sueldo == null || sueldo.isBlank()) return false;
        if (!sueldo.matches("^[0-9]+(\\.[0-9]{1,2})?$")) return false;
        return Double.parseDouble(sueldo) > 0;
    }

    /**
     * Genera un mensaje de error descriptivo si el sueldo es inválido.
     *
     * @param sueldo Cadena con el sueldo a evaluar.
     * @return Mensaje de error como {@code String}, o {@code null} si el sueldo es válido.
     */
    public static String mensajeSueldo(String sueldo) {
        if (sueldo == null || sueldo.isBlank())
            return "El sueldo base no puede estar vacío.";
        if (!sueldo.matches("^[0-9]+(\\.[0-9]{1,2})?$"))
            return "El sueldo solo puede contener números y hasta 2 decimales. Ejemplo: 8500.50";
        if (Double.parseDouble(sueldo) <= 0)
            return "El sueldo base debe ser mayor a $0.00.";
        return null;
    }

    /**
     * Verifica que el campo Alta IMSS sea un valor numérico positivo válido.
     * <ul>
     *   <li>Solo se permiten dígitos y un punto decimal opcional.</li>
     *   <li>Máximo 2 cifras decimales.</li>
     *   <li>El valor debe ser mayor a {@code 0}.</li>
     * </ul>
     *
     * @param alta Cadena con el valor de Alta IMSS a validar.
     * @return {@code true} si el valor es válido; {@code false} en caso contrario.
     *
     * @example Valor válido: {@code "350.00"}
     */
    public static boolean validarAltaIMSS(String alta) {
        if (alta == null || alta.isBlank()) return false;
        if (!alta.matches("^[0-9]+(\\.[0-9]{1,2})?$")) return false;
        return Double.parseDouble(alta) > 0;
    }

    /**
     * Genera un mensaje de error descriptivo si el valor de Alta IMSS es inválido.
     *
     * @param alta Cadena con el valor de Alta IMSS a evaluar.
     * @return Mensaje de error como {@code String}, o {@code null} si el valor es válido.
     */
    public static String mensajeAltaIMSS(String alta) {
        if (alta == null || alta.isBlank())
            return "El campo Alta IMSS no puede estar vacío.";
        if (!alta.matches("^[0-9]+(\\.[0-9]{1,2})?$"))
            return "Alta IMSS solo puede contener números y hasta 2 decimales.";
        if (Double.parseDouble(alta) <= 0)
            return "El valor de Alta IMSS debe ser mayor a 0.";
        return null;
    }
}