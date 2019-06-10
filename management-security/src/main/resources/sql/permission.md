sample
===
* 注释

	select #use("cols")# from permission  where  #use("condition")#

getPermissionsByUsername
===
    select p.id,p.name,p.url,p.parentid,p.sort,p.type,p.available from permission p inner join  
    role_permission rp on p.id=rp.permission_id inner join role r on r.id=rp.role_id inner join 
    user_role ur on ur.role_id=r.id inner join user u on u.id=ur.user_id  where u.username=#name# and  
    p.available=0 and r.available=0

cols
===
	id,name,url,parentid,sort,type,available

updateSample
===
	
	id=#id#,name=#name#,url=#url#,parentid=#parentid#,sort=#sort#,type=#type#,available=#available#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(url)){
	 and url=#url#
	@}
	@if(!isEmpty(parentid)){
	 and parentid=#parentid#
	@}
	@if(!isEmpty(sort)){
	 and sort=#sort#
	@}
	@if(!isEmpty(type)){
	 and type=#type#
	@}
	@if(!isEmpty(available)){
	 and available=#available#
	@}
	
	