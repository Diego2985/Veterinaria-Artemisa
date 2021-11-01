document.addEventListener('DOMContentLoaded', function () {
    var initialLocaleCode = 'es';
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        initialDate: new Date(),
        locale: initialLocaleCode,
        buttonIcons: true, // show the prev/next text
        weekNumbers: false,
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        dayMaxEvents: true, // allow "more" link when too many events
        selectable: true,
        selectMirror: true,
        select: function (arg) {
            var pickedDate = arg.jsEvent.target;
            var date=pickedDate.getAttribute("data-date")
            pickedDate.setAttribute("data-bs-toggle", "modal");
            pickedDate.setAttribute("data-bs-target", "#exampleModal");

            var fechaDeAtencion=document.getElementById("fecha-atencion")

            fechaDeAtencion.value=date

            calendar.unselect()
        },
        validRange: { start: new Date(),
            end: "2022-06-01"
        },
        events: traerEventos()
    });
    calendar.render();

});