$("[rel=comments]").popover({
    trigger : 'click',
    placement : 'bottom',
    sanitize: false,
    html: true,
    content : '<textarea class="popover-textarea"></textarea>',
    template: '<div class="popover-schedule"><div class="d-flex justify-content-between calendar-head"><div class="p-2 no-padding">1 Oktober 2020</div><div class="p-2 no-padding calendar-arrow">'+
    '<button type="button" class="btn btn-default popover-cancel">'+
    'x</button>' +
    '</div></div><div class ="d-flex flex-row date-card"><div class="p-2 width-20"><div class="h4 no-margin-bot">18</div>lör</div>  <div class="p-2 date-card-inner"><div class="h5 no-margin-bot">Sebastian Nilsson</div><div class="d-flex"><div class="p-2 no-padding">10:00-20:00</div><div class="p-2 no-padding padding-left gray-text">Middag</div></div><div class="d-flex justify-content-between"><div class="p-2 no-padding gray-text"><span data-feather="map-pin"></span> Skolvägen</div><div class="p-2 no-padding padding-right  gray-text"><span data-feather="coffee"></span> 17:00-17:30</div></div></div></div><div class ="d-flex flex-row date-card"><div class="p-2 width-20"><div class="h4 no-margin-bot">18</div>lör</div>  <div class="p-2 date-card-inner active"><div class="h5 no-margin-bot">Johan Nilsson</div><div class="d-flex"><div class="p-2 no-padding">10:00-20:00</div><div class="p-2 no-padding padding-left gray-text">Middag</div></div><div class="d-flex justify-content-between"><div class="p-2 no-padding gray-text"><span data-feather="map-pin"></span> Skolvägen</div><div class="p-2 no-padding padding-right  gray-text"><span data-feather="coffee"></span> 17:00-17:30</div></div></div></div><div class ="d-flex flex-row date-card"><div class="p-2 width-20"><div class="h4 no-margin-bot">18</div>lör</div>  <div class="p-2 date-card-inner"><div class="h5 no-margin-bot">Axel Ström</div><div class="d-flex"><div class="p-2 no-padding">10:00-20:00</div><div class="p-2 no-padding padding-left gray-text">Middag</div></div><div class="d-flex justify-content-between"><div class="p-2 no-padding gray-text"><span data-feather="map-pin"></span> Skolvägen</div><div class="p-2 no-padding padding-right  gray-text"><span data-feather="coffee"></span> 17:00-17:30</div></div></div></div></div>'

})

$("[rel=comments]").on('click', function() {
    //hide any visible comment-popover
    $("[rel=comments]").not(this).popover('hide');
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
