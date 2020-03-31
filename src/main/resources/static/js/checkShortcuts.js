let pToProfile, nToNotes;
$.ajax({
    url: "/shortcutStatus"
}).done(function(data) {
    if (data != null) {
        pToProfile = data["pToProfile"];
        nToNotes = data["nToNotes"];
    }
});

$(document).on('keypress', function(e) {
    switch (e.key) {
        case 'P': {
            if (pToProfile) {
                window.location.replace("/profile");
            }
            break;
        }
        case 'N': {
            if (nToNotes) {
                window.location.replace("/notes");
            }
            break;
        }
    }
});