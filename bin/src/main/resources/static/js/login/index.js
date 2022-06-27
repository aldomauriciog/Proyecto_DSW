//const init = () => {
//	document.querySelector("[type=submit]").addEventListener("click",login);
//}
//
//const login = async() => {
//	const body = new URLSearchParams();
//	body.append("usuario", document.querySelector("[name=usuario]").value);
//	body.append("clave", document.querySelector("[name=clave]").value);
//	const response = await fetch("auth",
//			{
//				method:"POST",
//				body
//			}).then(res=>res.json());
//	if(response) {
//		
//	}
//};
//
//window.addEventListener("load",init);