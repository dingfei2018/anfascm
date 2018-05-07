<%@ page contentType="text/html;charset=UTF-8" %>
<link type="text/css" rel="stylesheet" href="${ctxStatic}/anfa/jquery.qtip.min.css" />
<script src="${ctxStatic}/anfa/data-city.js?13" type="text/javascript"></script>
<script src="${ctxStatic}/anfa/jquery.placeholder.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/anfa/jquery.qtip.min.js"></script>
	<style type="text/css">
	  #address-div input.address-add{margin:12px 0px 0px 0px;}
	  .qtip{max-width: none;max-height: none;}
	  .qtip .qtip-content{padding: 0px}
	  .location-inline{    display: inline-block;width: 150px;margin: 15px 0px 0px 30px;line-height: 25px;vertical-align: middle;height: 25px;}
      .BMap_Marker img{max-width: none;}
      .anchorBL{display:none;}
      .BMap_cpyCtrl BMap_noprint anchorBL{display:none;}  
	</style>
<div class="control-group" id="address-div">
		<label class="control-label">${bookName}详细地址：</label>
		<div id="startId" class="controls">
		    <form:hidden path="book.id"/>
			<select id="loc_province" name="province" style="width:105px"></select> 
			<select id="loc_city" name="city" style="width:105px"></select>
			<select id="loc_area" name="book.regionCode" style="width:105px"></select>
			<form:input path="book.tailAddress" htmlEscape="false" maxlength="45" class="input-xlarge " placeholder="${bookName}详细地址" />
			<br>
			<form:input path="book.locationXY" htmlEscape="false" maxlength="45" class="input-xlarge address-add" placeholder="${bookName}地址经纬度" />
			<span class="location-inline"><a href="javascript:void(0)" class="showmappoint">经纬度取点</a></span>							
		</div>
</div>
<div id="map" style="width:1000px;height: 500px;display: none;position: relative;">
 <div style="position: absolute;top: 10px;left: 40%;width: 300px; z-index: 100;margin-left: -380px;">
 <input name="map-addr-input"  id="map-addr-input" type="text" value="" style="padding: 0px;    height: 25px;    margin: 0px;" placeholder="地址搜索"/>
 <input id="btn-addr-search"  type="button" value="搜索" onclick="search_map_point()"/></div>
 <a onclick="bottom_close()" href="javascript:void(0)" class="bottom-close" id="bottom_close" style="width: 30px;height:30px;position: absolute;top:0px;right:300px; display:none; z-index: 1;background-image:url('${ctxStatic}/anfa/sc.jpg'); "></a>
<div id="allmap" style="width: 100%;height: 100%;float:left" ></div>
<div id="map-right-list" style="width: 0px;height: 100%;float:left;position:relative;overflow:auto;background:white;">
<div id="r-result"  ></div>

</div></div>

<script type="text/javascript">

var map;
var lastmark;
function showSelectedRegion(part){
		 //$("#s2id_loc_province a span.select2-chosen").html($("#loc_province").find("option:selected").text());		
		 // $("#s2id_loc_city a span.select2-chosen").html($("#loc_city").find("option:selected").text());
		 // $("#s2id_loc_area a span.select2-chosen").html($("#loc_area").find("option:selected").text());
		 $('#loc_province').trigger('change.select2');
		 $('#loc_city').trigger('change.select2');
		 $('#loc_area').trigger('change.select2');
		 if($('#loc_city').html()==""){
			 $("#s2id_loc_city a span.select2-chosen").html(" - 请选择 - ");
		 }	
		 if($('#loc_area').html()==""){
			 $("#s2id_loc_area a span.select2-chosen").html(" - 请选择 - ");
		 }		 
}

function addressValidate(){
	var is_error=false;
	var messages="";
	var target=null;
	if ($("#loc_area").val()==null || $("#loc_area").val()==""){
		is_error = true
		messages="请选择区域";
		target=$("#s2id_loc_area");
    }else if ($("input[name='book.tailAddress']").val()==""){
    	is_error = true
		messages="请填写详细地址";
    	target=$("#book.tailAddress");
    }else if ($("input[name='book.locationXY']").val()==""){
    	is_error = true
		messages="请填写经纬度";
    	target=$("#book.locationXY");
    }	
	return [target,is_error, messages];
}

$('input').placeholder();
$("#startId").region({areaField:"book.regionCode",cityField:"city",currAreaCode:"${regionCode}", crossDomain:true,callback:function(part){showSelectedRegion(part);}});
      
function loadMap() {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=hwdLiBYBfNbLOUfOy5Pfm5s8zKQK0inN&callback=mapinit";
		document.body.appendChild(script);
}

function getSelectedOption(sel) {
  	    var opt;
  	    for ( var i = 0, len = sel.options.length; i < len; i++ ) {
  	        opt = sel.options[i];
  	        if ( opt.selected === true ) {
  	            break;
  	        }
  	    }
  	    return opt;
}
  	
	function getoption(target,defaultval) {
  		var sel = document.getElementById(target);
  		var opt = getSelectedOption(sel); 
  	    if (typeof(opt) == "undefined"){
  	    	return "undefined"
  	    }
  		if(opt.value=="" && typeof defaultval!="undefined"){
  			return defaultval;
  		}
  		return opt.text;
  	}
	
  	
  	function getFullAddress() {
  		var province = getoption('loc_province');
  		var city = getoption('loc_city');
  		var area = getoption('loc_area');
  		var tailaddr = document.getElementById("book.tailAddress").value;
  		var fulladdr="";
  		if(province !="undefined"){
  			fulladdr +=province;
  		} 
  		if(city !="undefined"){
  			fulladdr +=city;
  		} 
  		if(area !="undefined"){
  			fulladdr +=area;
  		} 
  		if(tailaddr !=""){
  			fulladdr +=tailaddr;
  		} 
  		return  fulladdr;			
  	}
  	function deletePoint(map){
		/* var allOverlay = map.getOverlays();
		for (var i = 0; i <allOverlay.length; i++){
				map.removeOverlay(allOverlay[i]);			
		} */
		
  		map.removeOverlay(lastmark);	
  	}
  	
  	function addPoint(map,point,exlusive){  	
  		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);               // 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		lastmark=marker;
  	}
	function mapinit() {
		var city = getoption('loc_city',"广州");
		var fulladdr= getFullAddress();
		map = new BMap.Map("allmap");            // 创建Map实例
		map.centerAndZoom(city,12);                 
		map.enableScrollWheelZoom();                 //启用滚轮放大缩小
		map.addEventListener("click",function(e){
			var locationxy= e.point.lng + "," + e.point.lat;
			document.getElementById('book.locationXY').value=locationxy;
			deletePoint(map);
			addPoint(map,e.point);
		});
		if(fulladdr != ""){
			var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			myGeo.getPoint(fulladdr, function(point){
				if (point) {
					map.centerAndZoom(point, 15);
					addPoint(map,point);
				}
			}, city);
		}	
    }  
	
	function search_map_point(){
		var key = document.getElementById('map-addr-input').value;
		if(key ==""){
			document.getElementById('allmap').style.width='100%';
			document.getElementById('map-right-list').style.width='0px';
			
		}else{
			var local = new BMap.LocalSearch(map, {
				renderOptions: {map: map, panel: "r-result"}
			});
			document.getElementById('allmap').style.width='700px';
			document.getElementById('map-right-list').style.width='300px';
			document.getElementById('bottom_close').style.display='block';
			local.search(key);
	
		}
		
	}

	function bottom_close(){
		document.getElementById('allmap').style.width='100%';
		document.getElementById('map-right-list').style.width='0px';
		document.getElementById('bottom_close').style.display='none';
	}
	$(document).ready(function () {	  
		var lastaddr="";
	    // This will automatically grab the 'title' attribute and replace
	    // the regular browser tooltips for all <a> elements with a title attribute!
		 $('.showmappoint').qtip({
	         show: 'click',
	         hide: 'unfocus',
	         content: {
                 text:  function(){
                	 var fulladdr= getFullAddress();
             		 if(fulladdr != "" && fulladdr!=lastaddr){
             			lastaddr = fulladdr;
                   	    loadMap();                   	
             		 }
             		 return $("#map");
                 }
             },
             position:{
            	 my:'top,Center',at:'bottom,Center',adjust: { y: 5,  resize: true},
            	 effect: function(api, pos, viewport) {
                     // "this" refers to the tooltip
                     $(this).animate(pos, {
                         duration: 600,
                         easing: 'linear',
                         queue: false // Set this to false so it doesn't interfere with the show/hide animations
                     });
                 }
             }
	     });
	    
	});
</script>