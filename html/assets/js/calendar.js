
$("[rel=comments]").popover({
    trigger : 'click',
    placement : 'bottom',
    sanitize: false,
    html: true,
    content : '<textarea class="popover-textarea"></textarea>',
    template: '<div class="appholder">asd</div>'
})

/*
'<div class="popover-schedule"><div class="d-flex justify-content-between calendar-head"><div class="p-2 no-padding">1 Oktober 2020</div><div class="p-2 no-padding calendar-arrow">'+
'<button type="button" class="btn btn-default popover-cancel">'+
'x</button>' +
'</div></div><div class ="d-flex flex-row date-card"><div class="p-2 width-20"><div class="h4 no-margin-bot">18</div>lör</div>  <div class="p-2 date-card-inner"><div class="h5 no-margin-bot">Sebastian Nilsson</div><div class="d-flex"><div class="p-2 no-padding">10:00-20:00</div><div class="p-2 no-padding padding-left gray-text">Middag</div></div><div class="d-flex justify-content-between"><div class="p-2 no-padding gray-text"><span data-feather="map-pin"></span> Skolvägen</div><div class="p-2 no-padding padding-right  gray-text"><span data-feather="coffee"></span> 17:00-17:30</div></div></div></div><div class ="d-flex flex-row date-card"><div class="p-2 width-20"><div class="h4 no-margin-bot">18</div>lör</div>  <div class="p-2 date-card-inner active"><div class="h5 no-margin-bot">Johan Nilsson</div><div class="d-flex"><div class="p-2 no-padding">10:00-20:00</div><div class="p-2 no-padding padding-left gray-text">Middag</div></div><div class="d-flex justify-content-between"><div class="p-2 no-padding gray-text"><span data-feather="map-pin"></span> Skolvägen</div><div class="p-2 no-padding padding-right  gray-text"><span data-feather="coffee"></span> 17:00-17:30</div></div></div></div><div class ="d-flex flex-row date-card"><div class="p-2 width-20"><div class="h4 no-margin-bot">18</div>lör</div>  <div class="p-2 date-card-inner"><div class="h5 no-margin-bot">Axel Ström</div><div class="d-flex"><div class="p-2 no-padding">10:00-20:00</div><div class="p-2 no-padding padding-left gray-text">Middag</div></div><div class="d-flex justify-content-between"><div class="p-2 no-padding gray-text"><span data-feather="map-pin"></span> Skolvägen</div><div class="p-2 no-padding padding-right  gray-text"><span data-feather="coffee"></span> 17:00-17:30</div></div></div></div></div>'


*/

$("[rel=comments]").on('click', function() {
    //hide any visible comment-popover
    $("[rel=comments]").not(this).popover('hide');
    var $this = $(this);
    //close on cancel
    $(".appholder").html(getHtml($(this).attr("data-date")))
    //console.log(this.getAttribute("data-date"))

    $('.popover-cancel').click(function() {
        $this.popover('hide');
    });
    //update link text on submit
    $('.popover-submit').click(function() {
        $this.text($('.popover-textarea').val());
        $this.popover('hide');
    });
});

function getHtml(key){
  console.log(2)
  console.log(key)
  let datt = "2020"
  list = data[key];
  if(list != null)
    datt = key
  console.log(list)
  html = ""
  html +=
  '<div class="popover-schedule">'+
    '<div class="d-flex justify-content-between calendar-head">'+
      '<div class="p-2 no-padding">'+datt+'</div>'+
      '<div class="p-2 no-padding calendar-arrow"><button type="button" class="btn btn-default popover-cancel">x</button></div>'+
    '</div>'


  if(list){
    for (var i = 0; i < list.length; i++) {
      obj = list[i];
      console.log(obj)
      html +=
      '<div class ="d-flex flex-row date-card">' +
        '<div class="p-2 width-20">' +
          '<div class="h4 no-margin-bot">'+ obj['datum'] +'</div>' +
          obj['dag'] +
        '</div>';
        if(obj["you"]){
          html += '<div class="p-2 date-card-inner active">'
        } else {
          html += '<div class="p-2 date-card-inner">'
        }
        html +=
          '<div class="h5 no-margin-bot">'+ obj['name'] +'</div>'+
          '<div class="d-flex">'+
            '<div class="p-2 no-padding">'+ obj['startid'] +'-'+ obj['sluttid'] +'</div>'+
            '<div class="p-2 no-padding padding-left gray-text">'+obj['category']+'</div>'+
          '</div>'+
          '<div class="d-flex justify-content-between">'+
            '<div class="p-2 no-padding gray-text">'+
              '<span data-feather="map-pin"></span> Skolvägen'+
            '</div>'+
            '<div class="p-2 no-padding padding-right  gray-text">'+
              '<span data-feather="coffee"></span> 17:00-17:30'+
            '</div>'+
          '</div>'+
        '</div>'+
      '</div>'
    }
  } else {
    html += '<div class ="d-flex flex-row date-card">Det finns inga pass för den här dagen</div>';
  }

  html += '</div>';
  console.log(html)
  return html;
}
