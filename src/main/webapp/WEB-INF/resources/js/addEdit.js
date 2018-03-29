$(document).ready(function($) {
	$("#editBtn").click(function() {
		if ($('[name="empChk"]:checked').length <= 0) {
			alert("Please select at least one chekbox");
			return;
		} else if ($('[name="empChk"]:checked').length > 1) {
			alert("Please Select only one checkbox");
			return;
		} else {
			$("#editForm #edit").val($('input[name="empChk"]:checked').val());
			$("#editForm").submit();
		}
	});

	$("#deleteBtn").click(function() {
		if ($('[name="empChk"]:checked').length <= 0) {
			alert("Please select at least one chekbox");
			return;
		} else {
			var selectedIds = [];
			$('input[name="empChk"]:checked').each(function() {
				selectedIds.push($(this).val());
			})
			$("#deleteForm #delete").val(selectedIds);
			$("#deleteForm").submit();
		}
	})

	$("#searchSubmitBtn").click(function() {
		if ($("#searchdropdown").val() == "0") {
			alert("Please select search by criteria")
			return false;
		} else {
			$("#searchForm").submit();
		}
	})

	$("#searchdropdown").unbind("change").change(function() {
		alert("search change");
		$('input[name="searchCriteria"]').val("");
		if ($("#searchdropdown").val() == "byEmpId") {
			$('input[name="searchCriteria"]').addClass("numeric");
			$('.numeric').on('input', function(event) {
				alert("on input");
				this.value = this.value.replace(/[^0-9]/g, '');
			});
		} else {
			$('input[name="searchCriteria"]').removeClass("numeric")
		}
	});

	$("#searchSubmitBtn1").click(function() {
		$("#searchForm1").submit();
	})

})
