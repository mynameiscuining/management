sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,username,password,locked,status

updateSample
===
	
	id=#id#,username=#username#,password=#password#,locked=#locked#,status=#status#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(username)){
	 and username=#username#
	@}
	@if(!isEmpty(password)){
	 and password=#password#
	@}
	@if(!isEmpty(locked)){
	 and locked=#locked#
	@}
	@if(!isEmpty(status)){
	 and status=#status#
	@}
	
	