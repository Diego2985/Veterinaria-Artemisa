// var modal = document.getElementById('modal-image')
// modal.addEventListener('show.bs.modal', function (event) {
//     var button = event.relatedTarget
//     var recipient = button.getAttribute('data-image')
//     var modalBody = modal.querySelector('.modal-body')
//
//     modalBody.innerHTML="<img src='/images/"+recipient+".jpg' alt='wide-img'/>";
// })

$('#modalImage').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var image = button.data('image') // Extract info from data-* attributes
    var title = button.data('title') // Extract info from data-* attributes
    var modal = $(this)
    modal.find('.modal-title').text(title)
    modal.find('.modal-body').html("<img src='data:image/jpeg;base64,"+image+"' style='height: 100%; width: 100%' alt='wide-img'/>")
})