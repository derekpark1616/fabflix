/**
 * 
 */

 // Original JavaScript code by Chirp Internet: www.chirp.com.au
  function CheckDate(field)
  {
    var allowBlank = true;
    var minYear = (new Date()).getFullYear();
    var minY = 1920;
    var maxYear = minYear+10;
    
    var errorMsg = "";

    // regular expression to match required date format
    re = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;

    if(field.value != '') {
      if(regs = field.value.match(re)) {
        if(regs[1] < minY || regs[1] > maxYear) {
          errorMsg = "Invalid value for year: " + regs[1] + " - must be between " + minY + " and " + maxYear;
        } else if(regs[2] < 1 || regs[2] > 12) {
          errorMsg = "Invalid value for month: " + regs[2];
        } else if(regs[3] < 1 || regs[3] > 31) {
          errorMsg = "Invalid value for day: " + regs[3];
        }
      } else {
        errorMsg = "Invalid date format: " + field.value;
      }
    } else if(!allowBlank) {
      errorMsg = "Empty date not allowed!";
    }

    if(errorMsg != "") {
      alert(errorMsg);
      field.focus();
      return false;
    }

    return true;
  }