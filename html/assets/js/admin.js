$("[rel=add-user]").popover({
    trigger : 'click',
    placement : 'bottom',
    sanitize: false,
    html: true,
    content : '<textarea class="popover-textarea"></textarea>',
    template: '<div class="popover-schedule">'+
    '<div class="d-flex justify-content-between calendar-head">'+
      '<div class="p-2 no-padding">Lägg till användare</div>'+
      '<div class="p-2 no-padding calendar-arrow"><button type="button" class="btn btn-default popover-cancel">x</button></div>'+
    '</div>'+
    '<div class="inner-add-user">'+
      '<form>'+
        '<div class="form-group">'+
          '<label for="name">Namn:</label>'+
          '<input type="text" class="form-control" id="name" aria-describedby="name" placeholder="Namn på användaren">'+
        '</div>'+
        '<div class="form-group">'+
          '<label for="username">Användarnamn:</label>'+
          '<input type="text" class="form-control" id="username" aria-describedby="name" placeholder="Användarnamn:">'+
        '</div>'+
        '<div class="form-group">'+
          '<label for="password">Lösenord</label>'+
          '<input type="password" class="form-control" id="password" placeholder="Lösenord">'+
        '</div>'+
        '<div class="form-group">'+
          '<label for="salary">Lön(kr/h):</label>'+
          '<input type="text" class="form-control" id="salary" aria-describedby="name" placeholder="Lön(kr/h):">'+
        '</div>'+
        '<div class="form-check">'+
          '<input type="checkbox" class="form-check-input" id="isadmin">'+
          '<label class="form-check-label" for="isadmin">Admin</label>'+
        '</div>'+
        '<button type="submit" class="btn btn-lg btn-primary btn-block btn-custom">Lägg  till användare</button>'+
      '</form>'+
    '</div>'+
  '</div>'


})

$("[rel=add-user]").on('click', function() {
    //hide any visible comment-popover
    console.log("hej")
    $("[rel=add-user]").not(this).popover('hide');
    var $this = $(this);
    //close on cancel
    $('.popover-cancel').click(function() {
        $this.popover('hide');
    });
    //update link text on submit
    $('.popover-submit').click(function() {
        $this.text($('.popover-textarea').val());
        $this.popover('hide');
    });
});
