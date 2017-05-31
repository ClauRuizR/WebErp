
function editarContacto(formId, formAction){

    document.getElementById(formId).action = formAction;
    document.getElementById(formId).submit();
}
