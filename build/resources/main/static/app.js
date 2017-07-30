$(function(){
    // Activate mobile nav toggle button
    $(".button-collapse").sideNav({edge: 'right'});

    // Activate close icons for dismissible alerts
    $('[data-close]').on('click',function(){
        $(this).parent().remove();
    });

    // Capture change event on file inputs
    $('input[type=file]').on("change",function(){
        var value = $(this).val().split('\\').pop();
        value = value == null || value == '' ? $(this).find('.placeholder').data('placeholder') : value;
        $(this).parent('.file-wrapper').find('.placeholder').text(value);
    });

    var onChange = function(obj) {
        // Get option style
        var style = $(obj.selEl).find('li.cs-selected').attr('style');

        // Set style of parent cs-select placeholder to style of option
        $(obj.selPlaceholder).attr('style',style);
    };

    var onLoad = function(obj) {
        // Get selected option
        var $option = $(obj.el).find('option[selected]');

        if($option.length > 0) {
            // Get option style
            var style = $(obj.el).find('option[selected]').attr('style');

            // Set style of parent cs-select placeholder to style of option
            $(obj.selPlaceholder).attr('style',style);

            // Mark selected item as selected
            $(obj.selEl).find('ul li[data-value="' + obj.el.value + '"]').addClass('cs-selected');
        } else {
            // Unmark list items as cs-selected, since first is selected by default
            $(obj.selEl).find('ul li').removeClass('cs-selected');

            // Set select element's value to empty string
            $(obj.el).val('');
        }
    };

    // Activate Codrops select magic
    (function() {
        [].slice.call( document.querySelectorAll( 'select' ) ).forEach( function(el) {
            new SelectFx(el,{onLoad:onLoad,onChange:onChange});
        });
    })();
});

    // Socket to notice admin

    var stompClient = null;
    function connect() {
        var socket = new SockJS('/websocket-notice');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/notices', function (notice) {
                showNotice(JSON.parse(notice.body).content);
            });
            
            stompClient.subscribe('/topic/accidents', function (notice) {
                  notifyMe("Khẩn Cấp : Tai nạn mới",JSON.parse(notice.body).content)
                  showNotice(JSON.parse(notice.body).content);


            });
              stompClient.subscribe('/topic/alerts', function (notice) {
                  notifyMe("Khẩn Cấp :",JSON.parse(notice.body).content)
                  showNotice(JSON.parse(notice.body).content);

            });
        });
    }
    function showNotice(message) {
            window.focus(),
        Materialize.toast(message, 10000);
    }

   connect();

   // request permission on page load
   document.addEventListener('DOMContentLoaded', function () {
     if (!Notification) {
       alert('Desktop notifications not available in your browser. Try Chromium.');
       return;
     }

     if (Notification.permission !== "granted")
       Notification.requestPermission();
   });

   function notifyMe(title, messagem) {
       Push.create(title, {
        body: messagem,
        icon: 'http://192.168.1.110:8080/icons/ic_alert.png',
        timeout: 4000,               // Timeout before notification closes automatically.
        vibrate: [100, 100, 100],    // An array of vibration pulses for mobile devices.
        onClick: function() {
            window.focus(),
            console.log(this);
             this.close();

        }
    });
   }
