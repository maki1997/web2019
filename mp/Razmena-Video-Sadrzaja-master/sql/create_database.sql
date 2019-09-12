����   3l  Servlets/VideoServlet  javax/servlet/http/HttpServlet serialVersionUID J ConstantValue        <init> ()V Code
   
  LineNumberTable LocalVariableTable this LServlets/VideoServlet; doGet R(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V 
Exceptions  javax/servlet/ServletException  java/io/IOException  id    %javax/servlet/http/HttpServletRequest   ! getParameter &(Ljava/lang/String;)Ljava/lang/String;
 # % $ java/lang/Integer & ' parseInt (Ljava/lang/String;)I  ) * + 
getSession "()Ljavax/servlet/http/HttpSession; - loggedInUser / 1 0 javax/servlet/http/HttpSession 2 3 getAttribute &(Ljava/lang/String;)Ljava/lang/Object; 5 
model/User
 7 9 8 DAO/VideoDAO : ; getVideoById (I)Lmodel/Video; = visiter ? unsuscribed A 	logedUser
 C E D model/Video F G getOwner ()Lmodel/User;
 4 I J K getId ()I
 M O N DAO/SubscribersDAO P Q findSubscribed (II)I S 	subscribe
 4 U V W getRole ()Lmodel/User$Role;
 Y [ Z model/User$Role \ ] toString ()Ljava/lang/String; _ ADMIN
 a c b java/lang/String d e equals (Ljava/lang/Object;)Z
 C g h i 	isBlocked ()Z
 4 g l blocked n java/util/HashMap
 m  q status s u t java/util/Map v w put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; y +com/fasterxml/jackson/databind/ObjectMapper
 x 
 x | } ~ writeValueAsString &(Ljava/lang/Object;)Ljava/lang/String; � application/json � � � &javax/servlet/http/HttpServletResponse � � setContentType (Ljava/lang/String;)V � � � � 	getWriter ()Ljava/io/PrintWriter;
 � � � java/io/PrintWriter � � write
 C I
 � � � DAO/LikeDislikeDAO � � getVideosLikeNumber (I)I
 C � � K getPreviews
 C � � � setPreviews (I)V
 7 � � � updateVideo (Lmodel/Video;)Z
 � � � DAO/CommentDAO � � getComments (I)Ljava/util/ArrayList;
 M � � � getSubscribersNumber � video � user � isSubscribed � comments � videoLikeNumber
 # � � � valueOf (I)Ljava/lang/Integer; � 	subNumber � java/lang/Exception request 'Ljavax/servlet/http/HttpServletRequest; response (Ljavax/servlet/http/HttpServletResponse; I session  Ljavax/servlet/http/HttpSession; Lmodel/User; Lmodel/Video; Ljava/lang/String; isSub data Ljava/util/Map; mapper -Lcom/fasterxml/jackson/databind/ObjectMapper; jsonData Ljava/util/ArrayList; LocalVariableTypeTable 5Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>; &Ljava/util/ArrayList<Lmodel/Comment;>; StackMapTable doPost
 a � � K hashCode � delete � unblock � add � edit � block � order � url � name � 
visibility � allowComments
 � � � java/lang/Boolean � � parseBoolean (Ljava/lang/String;)Z � allowRating � description � public	 � � � model/Video$Visibility � � PUBLIC Lmodel/Video$Visibility; � private	 � �  � PRIVATE	 � � UNLISTED java/util/Date
  2
 C
 
 �(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lmodel/Video$Visibility;ZILjava/util/Date;Lmodel/User;ZZZZII)V
 7 � addVideo success
 C setVisibility (Lmodel/Video$Visibility;)V
 C setAllowComments (Z)V
 C setAllowRating
 C � setDescription  videoId
 C"# 
setDeleted% column' ascDesc) userName
+-, DAO/UserDAO./ getByUserName  (Ljava/lang/String;)Lmodel/User;
 412 ] getUsername
 7456 OrderAllUserVideo <(ILjava/lang/String;Ljava/lang/String;)Ljava/util/ArrayList;
 7896 OrderPublicUserVideo; faliure= stat? videos
 CAB 
setBlocked visibil Z d Ljava/util/Date; vv visibili allowCommentss allowRatings descriptions visibilitys dataEdit 
mapperEdit jsonDataEdit idD videoD dataD mapperD 	jsonDataD e Ljava/lang/Exception; datao mappero 	jsonDatao idB videoB dataB mapperB 	jsonDataB idUB videoUB dataUB mapperUB 
jsonDataUB $Ljava/util/ArrayList<Lmodel/Video;>;f java/util/ArrayList 
SourceFile VideoServlet.java InnerClasses Role 
Visibility !                 
      3     *� �       
    (  *                              �+�  � ">+� ( :,� . � 4:� 6:<:>:� �@:� B� H� H� L6		� R:� B� �� T� X^� `� �� f� � B� j� �k:� mY� o:

p� r W� xY� z:
� {:,� � ,� � � ��� f� � B� j� Bk:� mY� o:		p� r W� xY� z:

	� {:,� � ,� � � ��� �� �6	� �`� �� �W� �� �:
� B� H� �6� mY� o:�� r Wp� r W�� r W�� r W�
� r W�	� �� r W�� �� r W� xY� z:� {:,� � ,� � � �� N�    �� � �� ��� �     � 1   2  3  4 " 5 ( 6 , 7 0 9 5 : 9 ; K < P = T ? n B � C � E � F � G � H � I � J � K � N � O � Q � R � S � T � U V W Z [( ]. _8 aE cN dZ ef fr g~ h� i� j� k� l� m� n� p� s    �   �      � � �   � � �  �  �  � � �  "� - �  (� � �  ,� q �  0� � �  K u � � 	 � 2 � � 
 �  � �  �  � �  � 2 � � 	 �  � � 
 �  � �  � � � 	8 � � � 
E � � � N  � � �  � � �  � �  �   *  � 2 � � 
 � 2 � � 	8 � � � 
N  � �  �   = � T 
   � / 4 C a a  ,� >>� �    �  �   �            
  1  4+p�  N+� ( :,� . � 4:-Y:� ӫ      �f��   :��   G x�   T /n
   a�č   nQ�N   {ֶ `����ض `�c��ڶ `� -��ܶ `� ���޶ `�ݧ�� `���+�  :+�  :+�  :	+�  � �6
+�  � �6+�  :	�� `� � �:� 	�� `� � �:� �:�Y�:� CY
�	:�W� mY� o:p� r W� xY� z:� {:,� � ,� � � ���+�  � "6� 6:+�  :+�  � �6+�  � �6+�  :�� `� � �:� �� `� � �:� �:����� �W� mY� o:p� r W� xY� z:� {:,� � ,� � � ���+�  � "6� 6:�!� �W� mY� o:p� r W� xY� z:  � {:!,� � ,� � !� ���:":#+$�  :$+&�  :%+(�  :&&�*:'� 1�0&� `� � T� X^� `� +'� H$%�3:#� '� H$%�7:#� 	:$:N� mY� o:$$<"� r W$>#� r W� xY� z:%%$� {:&,� � ,� � &� �� �+�  � "6''� 6:((�@(� �W� mY� o:))p� r W� xY� z:**)� {:+,� � ,� � +� �� _+�  � "6,,� 6:--�@-� �W� mY� o:..p� r W� xY� z://.� {:0,� � ,� � 0� �� �$' �    � i   z 	 {  |  ~ � � � � � � � � � � � � � � � � � � � � � � �" �E �K �T �a �j �s �{ �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� � �	 � � � �, �5 �> �F �Q �T �b �i �o �u �~ �� �� �� �� �� �� �� �� �� �� �� �� �� � � � �$ �- �6 �C �P �Y �b �j �u �x �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� � � � �  �( �3 �   < 9  4      4 � �   4 � �  	+ q �  # � �   - �  � � � �  � � � �  � �C � 	 � � �D 
 � � �D  � � � �  �  � �   � �  p � � " gEF E DG � T 5 � � j  � � s  � � � �  � � � � � � �H � � �ID � �JD � �K � � L � � L � � `L �  5M � 5 N � > O � b QP � i JQ � ~ 5R � � S �  � T � !� �= � "� �? � #� ^% � $� S' � %� H) � &� A � � ') UV $6 BW � $Y X � %b Y � &� QZ � '� J[ � (� 5\ � )� ] � *� ^ � +� N_ � ,� G` � - 2a � . b � /  c � 0 �   H T 5 � �  5M � ~ 5R � � �?d #6 BW � $� 5\ � ) 2a � . �  x � `    � a / 4 a  � S    � a / 4 a a a a a  �  �� o    � a / 4 a  � S    � a / 4 a             C a a  �  �� _    � a / 4 a  � ^� Q (   � a / 4 a                            ae a a a 4  �  $   � a / 4 a                            ae  �� J    � a / 4 a  � ^� [ g   hi     Y 4j@ � Ck@