## 🏥👨‍⚕ ️ClinicaUQ

Una clínica necesita un software para gestionar la información de sus pacientes, citas y servicios. El sistema debe incluir un modelo de suscripciones con dos tipos: Básica y Premium. La suscripción Básica ofrece ciertos servicios de forma gratuita, otros con descuento y algunos que requieren el pago completo. Por su parte, la suscripción Premium abarca un mayor número de servicios gratuitos, aunque algunos aún requieren del pago total.

Una vez registrado un paciente, el sistema debe permitir la gestión de citas, las cuales incluirán un ID, fecha, paciente, servicio y una factura.

Por lo tanto, el sistema deberá ofrecer las siguientes funcionalidades:

- Registro de pacientes (con el tipo de suscripción).
- Listado de pacientes.
- Agendamiento y visualización de citas. (Al agendar se debe validar que no se cruce con otra cita en el mismo horario. Además, se le debe enviar un email al paciente indicando los datos de la cita y el total pagado).
- Cancelación de citas.
- Generación de facturas (calculadas según el servicio y la suscripción del paciente).
- Listado de servicios disponibles.

Defina una lista de servicios e indique cuáles están incluídos en la suscripción básica y cuáles en la suscripción premium. También incluya otros servicios que no están en ninguna suscripción. Se debe hacer uso del patrón Builder y Factory (para las suscripciones).

### Tecnologías utilizadas
- Java 21
- JavaFX
- Maven

---

Universidad del Quindío 💚 - Ingeniería de sistemas y computación - 2024-2