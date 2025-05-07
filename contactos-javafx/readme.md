## 📒 Gestor de Contactos

El proyecto "Gestor de Contactos" es una aplicación de escritorio hecha en JavaFX, que permite a los usuarios almacenar y gestionar sus contactos de manera sencilla. Esta aplicación está diseñada para ayudar a los usuarios a mantener organizados sus contactos personales o profesionales, proporcionando una interfaz fácil de usar para gestionar información relevante.

Funcionalidades:

### Interfaz de Usuario:

Pantalla principal con una tabla que muestra la lista de contactos.
Formulario para crear un nuevo contacto. Campos de entrada para nombre, apellido, número de teléfono, día de cumpleaños, dirección de correo electrónico y la foto de perfil (investigar sobre FileChooser).
Botones para añadir, editar, eliminar y buscar contactos.

### Gestión de Contactos:

Crear: Permite al usuario añadir un nuevo contacto.
Leer: Muestra todos los contactos en una tabla. Además, incluye un ComboBox con dos opciones: 'Nombre' y 'Teléfono', lo que permite buscar contactos según el filtro seleccionado en el ComboBox.
Actualizar: Permite al usuario modificar la información de un contacto existente al seleccionarlo de la lista y editar los campos correspondientes.
Eliminar: Permite al usuario eliminar contactos seleccionados de la lista.
Buscar: dado el nombre del contacto debe retornar su información completa en una Alerta.

### Validaciones:

Asegurar que los campos de entrada no estén vacíos y que el formato del número de teléfono y correo electrónico (usar expresiones regulares) sea correcto antes de permitir la creación o actualización de un contacto. Además se debe validar que no hayan contactos repetidos.
