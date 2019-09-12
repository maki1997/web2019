.mainDiv{
	width: 95%;
    margin-top: 70px;
    display: inline-block;
    text-align: left; 
    margin-bottom: 30px;
    min-height: 800px;

}
.video{
	width: 75%;
    border-radius: 10px;
    margin: 0;
    margin-bottom: 10px;
    margin-left: 3%;
}
.videoInfo h3,p{
    margin-top: 5px;
}
.videoInfo{
    margin-left: 3%;
}

.desc{
	margin-top: 20px;
    margin-bottom: 20px;
}
.desc a img{
    float: left;
    padding: 0;
    margin: 0 1%;
    border-radius: 10px;
}
.desc p{
	max-height: 90px;
	clear: left;
	overflow-wrap: scroll;
}
.desc button{
	float: right;
	width: 120px;
	margin-right: 28%;
}

#nameAuthor{
	float: left;
	margin: 0;
	padding: 0;
	list-style: none;
}
.likesAndDislikes{
	display: inline-block;
    vertical-align: middle;
    margin-right:28%; 
}
#commDiv{
	overflow: scroll;
    width: 100%;
    border-radius: 10px;
    
}
#comms{
    width: 100%;
    text-align: left;
    margin-left: 3%;
}
#myComment{
     margin-left: 3%;
}
.row{
    border-radius: 10px;
}
#comm{
    display:inline-block;
    vertical-align: middle;
}
#commSelect{
    display:inline-block;
    vertical-align: middle;
}
#comment{
    width: 75%;
    margin: 0 auto;
    margin-bottom: 7px;
    margin-left: 1%;
}
#comment>p{
    display:inline-block;   
}
#commentOwner{
    font-weight: 600;
    margin-right: 5px;
    text-decoration: none;
    color: dimgray;
}
#commentOwner:hover{
    color: black;
}
#commentDate{
    font-size: 10px; 
    color: dimgrey;
}
#likes{
    margin: 0 5px;
}


@media only screen and (max-width: 990px) {
    #commDiv{
        max-height: none;
        height: auto;
        overflow: auto;
        margin-bottom: 40px;
    }
}
#authorPic{
	width:50px;
	height: 50px;

}
@media only screen and (max-width: 815px) {
    .video iframe{
        height: 350px;
    }
}
@media only screen and (max-width: 620px) {
    .video iframe{
        height: 305px;
    }
}
@media only screen and (max-width: 560px) {
    .video iframe{
        height: 250px;
    }
}
@media only screen and (max-width: 460px) {
    .video iframe{
        height: 220px;
    }
}