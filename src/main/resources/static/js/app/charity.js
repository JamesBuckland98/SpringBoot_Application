//dust.config.cache=false;



var injectCharity = (function (id) {

    $.ajax({//send the AJAX request
        type: "GET",
        url: "/api/charity/" + id,
        dataType: "json"
        , cache: false
        , timeout: 600000
        , async: true
        , encode: true
    })
        .done(function (data) {
            dust.render('charityInfo',
                data,
                function (err, out) {
                    console.log(out);
                    // `out` contains the rendered output.
                    document.getElementById('charityDetails').innerHTML = out;
                }
            )
        })
        .fail(function (jqXHR, textStatus, errorThrown) { //and what to do if it fails
            console.log("failed");
        });
})