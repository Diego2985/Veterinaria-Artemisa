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
        selectable: false,
        selectMirror: true,
        validRange: { start: new Date(),
            end: "2022-06-01"
        },
        events: traerEventos(),
        eventClick: function(info){
            $('#modalTitulo').html(info.event.title);
            $('#modalFecha').html(info.event.start.toLocaleString());
            $('#exampleModal').modal();
        }
    });
    calendar.render();
});