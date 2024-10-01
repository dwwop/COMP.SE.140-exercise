using System;
using System.Runtime.Serialization;
using System.Text;
using Newtonsoft.Json;

namespace SystemInfoService2DotNet.Models;

/// <summary>
///     List of running processes
/// </summary>
[DataContract]
public class ProcessDTO(long pid, string user, string time, string command)
    : IEquatable<ProcessDTO>
{
    /// <summary>
    ///     ID of the process
    /// </summary>
    /// <value>ID of the process</value>
    /// <example>2</example>
    [DataMember(Name = "pid", EmitDefaultValue = true)]
    public long Pid { get; set; } = pid;

    /// <summary>
    ///     Terminal associated with the process
    /// </summary>
    /// <value>Terminal associated with the process</value>
    /// <example>root</example>
    [DataMember(Name = "user", EmitDefaultValue = false)]
    public string User { get; set; } = user;

    /// <summary>
    ///     Minutes and seconds the process has been running
    /// </summary>
    /// <value>Minutes and seconds the process has been running</value>
    /// <example>01:13</example>
    [DataMember(Name = "time", EmitDefaultValue = false)]
    public string Time { get; set; } = time;

    /// <summary>
    ///     Command that started the process
    /// </summary>
    /// <value>Command that started the process</value>
    /// <example>whoami</example>
    [DataMember(Name = "command", EmitDefaultValue = false)]
    public string Command { get; set; } = command;

    /// <summary>
    ///     Returns true if ProcessDTO instances are equal
    /// </summary>
    /// <param name="other">Instance of ProcessDTO to be compared</param>
    /// <returns>Boolean</returns>
    public bool Equals(ProcessDTO other)
    {
        if (other is null) return false;
        if (ReferenceEquals(this, other)) return true;

        return
            (
                Pid == other.Pid ||
                Pid.Equals(other.Pid)
            ) &&
            (
                User == other.User ||
                (User != null &&
                 User.Equals(other.User))
            ) &&
            (
                Time == other.Time ||
                (Time != null &&
                 Time.Equals(other.Time))
            ) &&
            (
                Command == other.Command ||
                (Command != null &&
                 Command.Equals(other.Command))
            );
    }

    /// <summary>
    ///     Returns the string presentation of the object
    /// </summary>
    /// <returns>String presentation of the object</returns>
    public override string ToString()
    {
        var sb = new StringBuilder();
        sb.Append("class ProcessDTO {\n");
        sb.Append("  Pid: ").Append(Pid).Append("\n");
        sb.Append("  User: ").Append(User).Append("\n");
        sb.Append("  Time: ").Append(Time).Append("\n");
        sb.Append("  Command: ").Append(Command).Append("\n");
        sb.Append("}\n");
        return sb.ToString();
    }

    /// <summary>
    ///     Returns the JSON string presentation of the object
    /// </summary>
    /// <returns>JSON string presentation of the object</returns>
    public string ToJson()
    {
        return JsonConvert.SerializeObject(this, Formatting.Indented);
    }

    /// <summary>
    ///     Returns true if objects are equal
    /// </summary>
    /// <param name="obj">Object to be compared</param>
    /// <returns>Boolean</returns>
    public override bool Equals(object obj)
    {
        if (obj is null) return false;
        if (ReferenceEquals(this, obj)) return true;
        return obj.GetType() == GetType() && Equals((ProcessDTO)obj);
    }

    /// <summary>
    ///     Gets the hash code
    /// </summary>
    /// <returns>Hash code</returns>
    public override int GetHashCode()
    {
        unchecked // Overflow is fine, just wrap
        {
            var hashCode = 41;
            // Suitable nullity checks etc, of course :)

            hashCode = hashCode * 59 + Pid.GetHashCode();
            if (User != null)
                hashCode = hashCode * 59 + User.GetHashCode();
            if (Time != null)
                hashCode = hashCode * 59 + Time.GetHashCode();
            if (Command != null)
                hashCode = hashCode * 59 + Command.GetHashCode();
            return hashCode;
        }
    }

    #region Operators

#pragma warning disable 1591

    public static bool operator ==(ProcessDTO left, ProcessDTO right)
    {
        return Equals(left, right);
    }

    public static bool operator !=(ProcessDTO left, ProcessDTO right)
    {
        return !Equals(left, right);
    }

#pragma warning restore 1591

    #endregion Operators
}