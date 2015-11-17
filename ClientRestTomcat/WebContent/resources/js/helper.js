// Deprecated since version 0.8.0 
Handlebars.registerHelper("formatDate", function(datetime, format) {
	if (moment) {
		// can use other formats like 'lll' too
		format = DateFormats[format] || format;
		return moment(datetime).format(format);
	} else {
		return datetime;
	}
});

Handlebars.registerHelper('ifCond', function(v1, v2, options) {
	if (v1 == v2) {
		return options.fn(this);
	}
	return options.inverse(this);
});