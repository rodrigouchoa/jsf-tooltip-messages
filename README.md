##Tooltip Messages Tag

Tutorial em portugues no link abaixo:
- http://rodrigouchoa.wordpress.com/2013/09/17/jsf-2-exibir-erros-como-tooltip 



==================
   Known Issues
==================
- This lib assumes all input elements will have a JSF clientId written. At least in Mojarra,
it seems that clientId are only automatically generated if there are ajax behaviors attached.
So if your form doesn't have any ajax calls, make sure you explicitly give all elements an id.

- The tooltipMessages tag has to always be placed after all the form components.

- Compatible with JDK 6 and up.

- Tested against JSF 2.1 and JSF 2.2, with Primefaces and Richfaces.


Questions? Reach me at "rodrigo.uchoa at gmail.com"