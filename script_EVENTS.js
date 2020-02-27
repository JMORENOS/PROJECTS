$(document).ready(function(){
			$(".btn_click").click(function(){
				alert("Pulsar una vez el mouse sobre un elemento. ");
			});
			$(".btn_dblclick").dblclick(function(){
				alert("Pulsar el mouse dos veces seguidas sobre un elemento.");
			});
			$(".btn_menter").mouseenter(function(){
				alert("El mouse se sitúa encima de un elemento.");
			});
			$(".btn_mleave").mouseleave(function(){
				alert("El mouse, que estaba situado encima de un elemento, sale de él.");
			});
			$(".btn_hover").hover(function(){
				$(this).text("Hover");
			},function(){
				$(this).text("Sin hover");
			});
			$(".btn_mdown").mousedown(function(){
				alert("Momento en que presiona el botón, independientemente de si se suelta o no, vale tanto para el botón izquierdo como para el derecho.");
			});
			$(".btn_mup").mouseup(function(){
				alert("Soltar un botón del ratón después de hacer click. El evento se produce sólo en el momento de soltar el botón.");
			});
			$(".btn_mmove").mousemove(function(){
				alert(" El evento se produce al mover el ratón sobre un elemento.");
			});
			$(".btn_mover").mouseover(function(){
				alert(" El evento se produce al situarse el ratón sobre un elemento.");
			});
			$(".btn_toggle").click(function(){
				$("button:not(.btn_toggle)").toggle();
			});


			$(".input_key").keydown(function(){
		        $(".input_key").css("background-color", "yellow");
		        $(".input_key").val("Keydown");
		    });
		    $(".input_key").keyup(function(){
		    	$(".input_key").css("background-color", "pink");
		        $(".input_key").val("Keyup");
		    });

		    $(".input_focusio").focusin(function(){
		    	$(this).css('background-color','beige');	
		    });

		    $(".input_focusio").focusout(function(){
		    	$(this).css('background-color','white');	
		    });

		    $(".input_focus").focus(function(){
		    	$(this).css('border-color','red');	
		    	$(this).css('border-style','outset');	
		    	$(this).css('border-width','5px');	
		    });
		});
