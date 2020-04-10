let pToProfile, nToNotes;
$.ajax({
    url: "/shortcutStatus"
}).done(function(data) {
    if (data != null) {
        pToProfile = data["pToProfile"];
        nToNotes = data["nToNotes"];
    }
});

let keyMap = {49: false, 78: false,
              50: false, 80: false};

$(document).keydown(function(e) {
    if (e.keyCode in keyMap) {
        keyMap[e.keyCode] = true;
        // 1 + n
        if (keyMap[49] && keyMap[78]) {
            window.location.replace("/notes");
        }
        // 2 + p
        if (keyMap[50] && keyMap[80]) {
            window.location.replace("/profile");
        }
    }
}).keyup(function(e) {
    if (e.keyCode in keyMap) {
        keyMap[e.keyCode] = false;
    }
});
