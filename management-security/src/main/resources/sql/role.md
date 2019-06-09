sample
===
* 注释

	select #use("cols")# from role  where  #use("condition")#

getRolesByUsername
===
    select r.id,r.name,r.available from role r INNER JOIN user_role ur on r.id=ur.role_id inner join user u    
    on u.id=ur.user_id where u.username =#username# and r.available=0

cols
===
	id,name,available

updateSample
===
	
	id=#id#,name=#name#,available=#available#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(available)){
	 and available=#available#
	@}
	
	