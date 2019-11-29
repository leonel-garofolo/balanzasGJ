; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Sistema de Pesaje"
#define MyAppVersion "1.5"
#define MyAppPublisher "LG, Inc."
#define MyAppURL "http://www.example.com/"
#define MyAppExeName "SistemaDePesaje.exe"
#define MyAppPATH "SistemaDePesaje.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{8B36871F-B176-4C6A-96D5-6F7AD4735F8A}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName=c:\SistemaDePesaje
DisableProgramGroupPage=yes
OutputDir=D:\innoSetup
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes
DisableDirPage=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "D:\innoSetup\SistemaDePesaje\jre\*"; DestDir: "{app}\jre"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "D:\innoSetup\SistemaDePesaje\SistemaDePesaje.exe"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "D:\innoSetup\SistemaDePesaje\mysql-5.5.62-win32.msi"; DestDir: "{tmp}"; Flags: nocompression dontcopy
Source: "D:\innoSetup\SistemaDePesaje\bk_inicial.sql"; DestDir: "{app}\mysql"; Flags: ignoreversion
Source: "D:\innoSetup\SistemaDePesaje\script_restore.bat"; DestDir: "{app}\bin"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; IconFilename: "{app}\icono.jpg"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; IconFilename: "{app}\icono.jpg"; Tasks: desktopicon

[Run]
Filename: "{reg:HKLM\SOFTWARE\MySQL AB\MySQL Server 5.5,Location}\bin\mysqld.exe"; Parameters: "--install"; WorkingDir: "{reg:HKLM\SOFTWARE\MySQL AB\MySQL Server 5.5,Location}\bin"; StatusMsg: "Installing MySQL Service";Description: "Installing MySQL Service"; Flags: runhidden; Check: MySQL_Is
Filename: C:\SistemaDePesaje\mysql\bin\MySQLInstanceConfig.exe; Parameters: "MySQLInstanceConfig.exe -i -q ServerType=DEVELOPMENT DatabaseType=MIXED ConnectionUsage=DSS Port=3306 ServiceName=MySQL55 RootPassword=1234"; StatusMsg: "Configurando Instancia de Base de datos"
Filename: C:\SistemaDePesaje\mysql\bin\script_restore.bat; StatusMsg: "Importando base de datos"

[Code]
function MySQL_Is(): Boolean;
var
iResultCode: Integer;
begin
  Result := true;
  if (not RegKeyExists(HKLM, 'SOFTWARE\MySQL AB\MySQL Server 5.5')) or 
   (not FileExists(ExpandConstant('{reg:HKLM\SOFTWARE\MySQL AB\MySQL Server 5.5,Location}\bin\mysql.exe'))) 
  then begin
     ExtractTemporaryFile('mysql-5.5.62-win32.msi');
     Exec('msiexec.exe', '/i mysql-5.5.62-win32.msi /qn INSTALLDIR="C:\SistemaDePesaje\mysql"', 
      ExpandConstant('{tmp}'), SW_HIDE, ewWaitUntilTerminated, iResultCode);
         if not FileExists(ExpandConstant('{reg:HKLM\SOFTWARE\MySQL AB\MySQL Server 5.5,Location}\bin\mysql.exe')) then begin
            MsgBox('Something went wrong! Installation should be terminated', 
              mbInformation, MB_OK);
            Result := false;
         end;
  end;
end;