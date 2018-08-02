//dust.config.cache=false;

var template = "Thank you for supporting {name}." +
    "<H1>{acronym}</h1>" +
    "<img src=\"/images/{logoPath}.png\"/>";


// Compile the template under the name 'hello'
var compiled = dust.compile(template, 'charityInfo');
// Register the template with Dust
dust.loadSource(compiled);

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