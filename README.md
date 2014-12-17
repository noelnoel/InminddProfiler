Inmindd Profiler and Support Environment 
===============

Version (1.3) 

===============

1. Developed with GWT for use on appengine. 

2. Branching model being used: http://nvie.com/posts/a-successful-git-branching-model/

  1. The jimod fork is being kept seperate in order to keep a clean (production) master fork on noelnoel in case of non-developers mistakenly editing code when logging issues. 

  2. All work should be carried out on a hotfix or feature branch (locally) as per branching model and merged to development when completed. When development is tested successfully locally it can then be pushed to remote branch. 

  3. Once the development branch is tested successfully, it can then be merged into master at which point the updates can be deployed to the appengine. 
3. French characters - see http://www.i18nqa.com/debug/utf8-debug.html and http://www.starr.net/is/type/htmlcodes.html for HTML codes:
  1. Ã¨ = &egrave; 
  2. Ã© = &eacute;
  3. Ãª = &ecirc;
  4. Ã = &agrave;
  5. Ã¡ = &aacute;
  6. Ã¢ = &acirc;
  4. Ã¹ = &ugrave;
  5. Ãº = &uacute;
  6. Ã» = &ucirc;
  7. Ã¬ = &igrave; 
  8. Ã­ = &iacute;
  9. Ã® = &icirc;

