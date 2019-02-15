package com.apll.centermanagementsservice.mail


data class User (

        var emailAddress: String?= null,

        var subject: String?= null,

        var message: String?= null
){
    constructor(cName:String,wName:String):this(
            "pkazad23@gmail.com",
            "Demo Mail",
            "Plot No 34 B, Dwaraka Sinman, 2nd floor," +
                    "Huda Heights, Near Lotus pond," +
                    "MLA Colony, Road No. 12," +
                    "Banjara Hills, Phase 2," +
                    "Film Nagar,, Hyderabad," +
                    "Telangana 500034" +
                    "" +
                    "Dear Mr./Mrs.:" +
                    "SYNYCS provides effective solutions in web designing, real time data analytics, software development," +
                    "mobile application and custom software Development Company.." +
                    "" +
                    "Respectfully yours," +
                    "" +
                    "SYNYCS Team."
    )
}


