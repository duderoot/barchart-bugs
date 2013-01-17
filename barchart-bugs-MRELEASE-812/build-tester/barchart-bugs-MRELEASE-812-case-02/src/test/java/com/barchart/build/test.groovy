
import java.util.regex.Pattern
import hudson.model.*

def job() {
  def text = Thread.currentThread().getName()
  def pattern = Pattern.compile("job/(.*)/build")
  def matcher = pattern.matcher(text); matcher.find()
  def name = matcher.group(1)
  def job = Hudson.instance.getJob(name)
}

def list(dir, tag) {
  def command = [ "/bin/bash", "-c", "cd '${dir}' ; git fetch --tags &> /dev/null ; git tag -l '${tag}'" ]
  def process = command.execute(); process.waitFor()
  def result = process.in.text.tokenize("\n")
}

class Version {
  String major, minor, patch
  def String toString() { String.format('%05d-%05d-%05d', major.toInteger(), minor.toInteger(), patch.toInteger())  }
}

def version(text) {
  def pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)")
  def matcher = pattern.matcher(text); matcher.find()
  new Version( major:matcher.group(1), minor:matcher.group(2), patch:matcher.group(3) )
}
  
try {
 
  def tagList = list( job().workspace, "barchart*" )
	 
  if (tagList == null) return [ 'master' ]
	
  def versionList = tagList.collect { version(it) }
	
  def resultList = versionList.reverse()
  
  resultList[0]
	
} catch(e) {
  
  e
  
}
