#! env coffee

ERROR_WRITING_OUTPUT  = 1

program = require 'commander'
fs      = require 'fs'


program
  .usage('<request name camelCased> <JSON string payload>')
  .parse(process.argv)


requestName = program.args.shift()
payload     = program.args.shift()

if !requestName|| !payload
  program.help()

payload = JSON.parse(payload)

capitalize = (str) -> str.charAt(0).toUpperCase() + str.slice(1);

capitalToHyphen = (str) -> str.replace(/[A-Z]/g, (char) -> "-#{char.toLowerCase()}")

className         = "#{ capitalize requestName }Request"
classAttributes   = ""
constructorParams = ""
constructorBody   = ""
toReadableBody    = ""
gettersAndSetters = ""

for attr, type of payload
  classAttributes   += "    private #{type} #{attr};\n"
  constructorParams += "#{type} #{attr}, "
  constructorBody   += "        this.set#{ capitalize attr }(#{ attr });\n"
  toReadableBody    += "        result += \"#{ attr }\" + k + #{ attr } + p;\n"
  gettersAndSetters += """

                       \t@XmlElement(name = "#{ attr }")
                           public #{ type } get#{ capitalize attr }() {
                               return #{ attr };
                           }

                           public void set#{ capitalize attr }(#{ type } #{ attr }) {
                               this.#{ attr } = #{ attr };
                           }

                       """
constructorParams = constructorParams.replace /, $/, ''  # Removes last of ", "
toReadableBody = toReadableBody.replace /" + p;\n$"/, ';\n'  # Removes last of " + p"


outputFilename = "../java/com/fiuba/taller/service/requests/#{ className }.java"
output = """
        package com.fiuba.taller.service.requests;

        import javax.xml.bind.annotation.XmlElement;
        import javax.xml.bind.annotation.XmlRootElement;

        @XmlRootElement(name = "#{ capitalToHyphen requestName }-request")
        public class #{ className } {

        #{ classAttributes }

            public #{ className }(){}

            public #{ className }(#{ constructorParams })
            {
                super();
        #{ constructorBody }
            }

            private String toReadable(String keyValSeparator, String propSeparator) {
                String result = "";
                String k = keyValSeparator;
                String p = propSeparator;

        #{ toReadableBody }
                return result;
            }

            @Override
            public String toString(){
                return toReadable("=", "&");
            }

            public String toJSON(){
                return "{" + toReadable(": ", ", ") + "}";
            }

        #{ gettersAndSetters }
        }
        """


fs.writeFile outputFilename, output, (err) ->
  if err
    console.error 'Error writing to output file.\n', err
    process.exit ERROR_WRITING_OUTPUT
