<br>
<#function getFieldList fieldList>
 	<#assign rtn>
 		<#assign index=0>
		<#list fieldList as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
			<#if index % 3 == 0>
			<tr>
			</#if>
				<td align="right" style="width:10%;" class="formTitle" nowrap="nowarp" ><span i18nkey="${field.fieldName}">${field.fieldDesc}</span>:</td>
				<td style="width:23%;" class="formInput">
					<@input field=field/>
				</td>
				<#if index % 3 == 0 && !field_has_next>
				<td style="width:10%;" class="formTitle"></td>
				<td style="width:23%;" class="formInput"></td>
				<td style="width:10%;" class="formTitle"></td>
				<td style="width:23%;" class="formInput"></td>
				</#if>
				<#if index % 3 == 1 && !field_has_next>
				<td style="width:10%;" class="formTitle"></td>
				<td style="width:23%;" class="formInput"></td>
				</#if>
			<#if index % 3 == 2 || !field_has_next>
			</tr>
			</#if>
			<#assign index=index+1>
			</#if>
		</#list>
 	</#assign>
	<#return rtn>
</#function>
<#function setTeamField teams>
 	<#assign rtn>
		 <#list teams as team>
			<#assign count=0>
			 <#list team.teamFields as teamfile>
					<#if teamfile.valueFrom != 2>
						<#assign count=count + 1>	
					</#if>
				</#list>
				<#if count !=0>
				<tr>
					<td colspan="6" class="teamHead">${team.teamName}</td>
				</tr>
				${getFieldList(team.teamFields)}
				</#if>
				
		</#list>
	</#assign>
	<#return rtn>
</#function>
<div class="subTableToolBar l-tab-links" >
	<a class="link add" atype="add" href="javascript:;" onclick="return false;" >${formLanguage.add}</a>
</div>
<div  formType="edit" class="block">
	<table class="listTable" >
		<tr>
			<td colspan="6"  class="formHead" >${table.tableDesc }</td>
		</tr>
		<#if teamFields??>
			<#if isShow>
				<#if showPosition == 1>
					${setTeamField(teamFields)}
					${getFieldList(fields)}
				<#else>
					${getFieldList(fields)}
					${setTeamField(teamFields)}
				</#if>
			<#else>
				${setTeamField(teamFields)}
			</#if>
		<#else>
			${getFieldList(fields)}
		</#if>
	</table>
</div>
	
<br>
