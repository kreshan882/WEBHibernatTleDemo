/*
 Authur      : Chandika Jayawardena
 Version     : Initial verison(1.0)
 Create Date : 28 June 2017
 Module      : scripts
 Descript    : This modue contains the client side functionalites of the application module
 
 */

var utilityManager = (function () {

    //Private methods and variables


    return {
        //Public methods and variables

        //Toggle left side navigation 
        dropDownMnuClick: function (ele) {
            var _ele = $(ele);

            if (!$(_ele).hasClass('expand')) {
                $(_ele).next('.sub-nav').slideDown('fast', function () {
                    _ele.addClass('expand');
                });
            } else {
                $(_ele).next('.sub-nav').slideUp('fast', function () {
                    _ele.removeClass('expand');
                });
            }

            $('.navigation > li a').each(function () {
                if ($(this).hasClass('expand')) {
                    $(this).removeClass('expand');
                    $(this).next('.sub-nav').slideUp('fast');
                }
            });
        },
        //Handle maximize and minimize feature
        toggleMaxMinDWidget: function (ele) {
            var _ele = $(ele).parent('div').parent();
            var _current = _ele.index();
            if (!$(ele).hasClass('expand')) {
                $('.d-col').each(function () {
                    if ($(this).index() != _current) {
                        $(this).hide();
                    }
                });
                $(ele).addClass('expand');
                $(ele).find('i').removeClass('fa-expand').addClass('fa-compress');
                $(ele).attr('title', 'Minimize');
                $('.d-col').eq(parseInt(_ele.index()) - 1).css({'width': 'calc(100% - 14px)', 'height': 'calc(100% - 54px)'});

            } else {
                _ele.attr('style', '');
                $(ele).removeClass('expand');
                $(ele).attr('title', 'Maximize');
                $(ele).find('i').removeClass('fa-compress').addClass('fa-expand');
                $('.d-col').show();
            }

            setTimeout(function () {
                $(window).trigger('resize');
            }, 10);
        },
        //Show form success or error messages
        showMessage: function (ele, msg, cls, stoken) {
            var c= window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
         
            if(msg.toLowerCase() == 'Password successfully changed'.toLowerCase()){
                var c= window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
                window.location = c+'/logOut.action?message=success1';
            }
            var icon = '';
            if (cls.toLowerCase() == 'errormsg') {
                icon = 'fa-times';
            } else if (cls.toLowerCase() == 'successmsg') {
                icon = 'fa-check';
            }
            $( "input[name='RequstToken']" ).val(stoken);
            $(ele).removeClass('errormsg').removeClass('successmsg').addClass(cls);
            $(ele).find('i').removeClass('fa-times').removeClass('fa-check').addClass(icon);
            $(ele).find('span').html(msg);
            $(ele).fadeIn('fast');
        },
        //Add icons for buttons
        insertBtnIcons: function () {
            var _html = '';
            $('.searchicon').each(function () {
                _html = "<i class='fa fa-search'></i>" + $(this).html();
                $(this).html(_html);
            });

            $('.addicon').each(function () {
                _html = "<i class='fa fa-plus'></i>" + $(this).html();
                $(this).html(_html);
            });

            $('.reseticon').each(function () {
                _html = "<i class='fa fa-times'></i>" + $(this).html();
                $(this).html(_html);
            });

        },
        //Page event bindings
        bindPageEvents: function () {

            $('.navigation > li a').on('click', function () {
                utilityManager.dropDownMnuClick(this);
            });

            $('.do-nothing').on('click', function (e) {
                e.preventDefault();
            });

            utilityManager.setDatePickerImg(); //Set datepicker image

            /* Dashboard widget events */
            $('.ddl-daterange').on('click', function () {
                var _ele = $(this);
                if (!$(_ele).hasClass('expand')) {
                    $(_ele).next('.rangepopup').slideDown('fast', function () {
                        _ele.addClass('expand');
                    });
                } else {
                    $(_ele).next('.rangepopup').slideUp('fast', function () {
                        _ele.removeClass('expand');
                    });
                }
            });

            $('.rangepopup button').on('click', function () {
                var _ele = $(this);
                $(_ele).parent('.rangepopup').slideUp('fast', function () {
                    _ele.parent('.rangepopup').prev('.ddl-daterange').removeClass('expand');
                });
            });

            $('.maxmin').on('click', function () {
                utilityManager.toggleMaxMinDWidget(this);
                var _self = $(this);
                if ($(this).hasClass('gridtable')) {
                    setTimeout(function () {
                        GridRowCount.JqGridRows(0, 'gridtable');
                    }, 100);
                } else if ($(this).hasClass('gridtable2')) {
                    setTimeout(function () {
                        GridRowCount.JqGridRows(1, 'gridtable2');
                    }, 100);
                } else if ($(this).hasClass('gridtable3')) {
                    setTimeout(function () {
                        GridRowCount.JqGridRows(2, 'gridtable3');
                    }, 100);
                } 

                $('#graph').empty();
                window.m = chart();
                    
                setTimeout(function () {
                    
                    $(window).trigger('resize');
                }, 500);
                
            });

            $(window).resize(function () {
                $width = $('.custom-grid').eq(0).width();
                var $self = $("#gridtable");
                shrinkToFit = $self.jqGrid("getGridParam", "shrinkToFit");
                $self.jqGrid("setGridWidth", $width, shrinkToFit);

                $alert_width = $('.custom-grid').eq(1).width();
                var $alert_self = $("#gridtable2");
                alert_shrinkToFit = $alert_self.jqGrid("getGridParam", "shrinkToFit");
                $alert_self.jqGrid("setGridWidth", $alert_width, alert_shrinkToFit);
                
                $oper_width = $('.custom-grid').eq(2).width();
                var $oper_self = $("#gridtable3");
                oper_shrinkToFit = $oper_self.jqGrid("getGridParam", "shrinkToFit");
                $oper_self.jqGrid("setGridWidth", $oper_width, oper_shrinkToFit);
            });
            /* End */
        },
        //submit anchor tag function
        anchorTagSubmit: function (click, form) {
            $(click).on('click', function () {
                $(form).submit();
            });
        },
        resetMessage: function () {
            $('.add-form-msg').hide();
            $('.add-form-msg1').hide();
            $('.del-user-msg').hide();
            $('.del-user-msg1').hide();
            $('.edit-form-msg').hide();
        },
        setDatePickerImg: function () {
            var img = _PATH + '/resources/theme/darknight/assets/images/datepicker.png';
            $('.ui-datepicker-trigger').attr('src', img);
        }
    };

})();


$(document).ready(function () {
    utilityManager.bindPageEvents();
});
